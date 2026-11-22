import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

/**
 * Builds data/catalog/catalog.html from products.csv, brands.json, categories.json and logos/*.svg.
 * The page has an EN | DE | TR language switch. Brand names are never translated;
 * product names, sizes, categories and logo taglines are.
 *
 * Run from the project root:  java tools/BuildCatalogPage.java
 */
public class BuildCatalogPage {

    static final String[] LANGS = {"en", "de", "tr"};
    static final int LOW_STOCK_LIMIT = 10;

    public static void main(String[] args) throws Exception {
        Path dir = Path.of("data", "catalog");
        List<Map<String, String>> brandList = readJsonObjects(dir.resolve("brands.json"));
        Map<String, Map<String, String>> brands = new LinkedHashMap<>();
        for (var b : brandList) brands.put(b.get("key"), b);
        Map<String, Map<String, String>> categories = new LinkedHashMap<>();
        for (var c : readJsonObjects(dir.resolve("categories.json"))) categories.put(c.get("key"), c);
        List<Map<String, String>> products = readCsv(dir.resolve("products.csv"));

        // Products grouped by category, in the order of categories.json
        Map<String, List<Map<String, String>>> byCategory = new LinkedHashMap<>();
        for (String key : categories.keySet()) byCategory.put(key, new ArrayList<>());
        for (var p : products) {
            var list = byCategory.get(p.get("category_key"));
            if (list == null) throw new IllegalStateException("Unknown category " + p.get("category_key") + " in " + p.get("sku"));
            list.add(p);
        }
        byCategory.values().removeIf(List::isEmpty);

        long out = products.stream().filter(p -> qty(p) == 0).count();
        long low = products.stream().filter(p -> qty(p) > 0 && qty(p) < LOW_STOCK_LIMIT).count();

        StringBuilder h = new StringBuilder(Files.readString(Path.of("tools", "catalog-template-head.html")));

        // Channel chooser (US-39): shown first, the page then uses the chosen channel's colors
        Path ch = Path.of("assets", "channels");
        h.append("<div id=\"channel-chooser\" class=\"chooser\" role=\"dialog\" aria-modal=\"true\" aria-labelledby=\"chooser-title\">")
         .append("<div class=\"chooser-inner\"><div class=\"chooser-top\"><h2 id=\"chooser-title\" data-i18n=\"chooseChannel\">Choose a channel</h2>")
         .append("<div class=\"lang\" role=\"group\" aria-label=\"Language\">")
         .append(langButton("en", "English", FLAG_GB.replace("gb-clip", "gb-clip-2")))
         .append(langButton("de", "Deutsch", FLAG_DE))
         .append(langButton("tr", "Türkçe", FLAG_TR))
         .append("</div></div><p class=\"chooser-hint\" data-i18n=\"chooseHint\">Both channels sell the same products at the same prices – only the look changes.</p>")
         .append("<div class=\"channel-cards\">")
         .append(channelCard("htgo", "HTGO Market", svgDataUri(ch.resolve("htgo-market.svg")), svgDataUri(ch.resolve("htgo-market-icon.svg"))))
         .append(channelCard("gk", "GK Market", svgDataUri(ch.resolve("gk-market.svg")), svgDataUri(ch.resolve("gk-market-icon.svg"))))
         .append("</div></div></div>\n");

        h.append("<header><div class=\"wrap\"><div class=\"top\"><h1 data-i18n=\"title\">VetisPilot demo catalog</h1>")
         .append("<div class=\"top-right\"><div class=\"channel-pill\" hidden><img id=\"channel-icon\" alt=\"\" width=\"30\" height=\"30\">")
         .append("<span id=\"channel-name\"></span><button type=\"button\" id=\"switch-channel\" data-i18n=\"switchChannel\">Switch channel</button></div>")
         .append("<div class=\"lang\" role=\"group\" aria-label=\"Language\">")
         .append(langButton("en", "English", FLAG_GB))
         .append(langButton("de", "Deutsch", FLAG_DE))
         .append(langButton("tr", "Türkçe", FLAG_TR))
         .append("</div></div></div>\n")
         .append("<p data-i18n=\"intro\">").append(products.size()).append(" fictional products across ")
         .append(byCategory.size()).append(" categories, sold under ").append(brands.size())
         .append(" fictional brands. All brand names and logos were created for this portfolio project and do not represent real companies.</p>\n")
         .append("<div class=\"stats\">")
         .append(stat(products.size(), "products", "products"))
         .append(stat(brands.size(), "brands", "brands"))
         .append(stat(byCategory.size(), "categories", "categories"))
         .append(stat(out, "outOfStock", "out of stock"))
         .append(stat(low, "lowStock", "low stock"))
         .append("</div></div></header>\n");

        h.append("<main class=\"wrap\"><h2><span data-i18n=\"brandsTitle\">Brands</span> <span class=\"count\">")
         .append(brands.size()).append("</span></h2><div class=\"brands\">");
        for (var b : brands.values()) {
            long count = products.stream().filter(p -> p.get("brand_key").equals(b.get("key"))).count();
            h.append("<button type=\"button\" class=\"brand\" data-brand=\"").append(b.get("key")).append("\" data-brand-name=\"")
             .append(esc(b.get("name"))).append("\" data-count=\"").append(count).append("\"><img src=\"")
             .append(svgDataUri(dir.resolve(b.get("logo")))).append("\"");
            h.append(" data-src-de=\"").append(svgDataUri(dir.resolve(b.get("logo_de")))).append("\"");
            h.append(" data-src-tr=\"").append(svgDataUri(dir.resolve(b.get("logo_tr")))).append("\"");
            h.append(" data-src-en=\"\" alt=\"").append(esc(b.get("name"))).append(" logo\" width=\"300\" height=\"80\"></button>");
        }
        h.append("</div>\n")
         .append("<div id=\"brand-filter\" class=\"filterbar\" hidden><span id=\"brand-filter-text\"></span>")
         .append("<button type=\"button\" id=\"brand-filter-clear\" data-i18n=\"showAll\">Show all</button></div>\n");

        for (var e : byCategory.entrySet()) {
            var c = categories.get(e.getKey());
            h.append("<section><h2><span").append(i18nAttrs(c.get("name"), c.get("name_de"), c.get("name_tr"))).append(">")
             .append(esc(c.get("name"))).append("</span> <span class=\"count\">").append(e.getValue().size())
             .append("</span></h2><div class=\"grid\">");
            for (var p : e.getValue()) {
                var b = brands.get(p.get("brand_key"));
                if (b == null) throw new IllegalStateException("Unknown brand " + p.get("brand_key") + " in " + p.get("sku"));
                String brand = p.get("brand");
                String en = shortName(p.get("name"), brand), de = shortName(p.get("name_de"), brand), tr = shortName(p.get("name_tr"), brand);
                boolean byWeight = "WEIGHT".equals(p.get("sold_by"));
                h.append("<article class=\"card\" data-brand=\"").append(p.get("brand_key")).append("\"><div class=\"thumb\" style=\"background:").append(b.get("light_color")).append("\">")
                 .append("<img src=\"").append(svgDataUri(dir.resolve(b.get("icon")))).append("\" alt=\"\" width=\"56\" height=\"56\"></div>\n")
                 .append("<div class=\"body\"><p class=\"brandname\" style=\"color:").append(b.get("primary_color")).append("\">")
                 .append(esc(brand)).append("</p><h3").append(i18nAttrs(en, de, tr)).append(">").append(esc(en)).append("</h3>")
                 .append("<p class=\"meta\"><span").append(i18nAttrs(p.get("size"), p.get("size_de"), p.get("size_tr"))).append(">")
                 .append(esc(p.get("size"))).append("</span> · ").append(esc(p.get("sku"))).append("</p>\n");
                if (byWeight) {
                    double perKg = Double.parseDouble(p.get("price_eur")) / Double.parseDouble(p.get("weight_kg"));
                    h.append("<p class=\"weight\"><span data-i18n=\"byWeight\">Sold by weight</span> · <span data-price-kg=\"")
                     .append(String.format(Locale.ROOT, "%.2f", perKg)).append("\">€")
                     .append(String.format(Locale.ROOT, "%.2f", perKg)).append(" / kg</span></p>\n");
                }
                h.append("<div class=\"row\"><span class=\"price\" data-price=\"").append(p.get("price_eur")).append("\">€")
                 .append(String.format(Locale.ROOT, "%.2f", Double.parseDouble(p.get("price_eur")))).append("</span>")
                 .append(stockTag(qty(p))).append("</div></div></article>");
            }
            h.append("</div></section>\n");
        }
        h.append("</main>\n<footer class=\"wrap\" data-i18n=\"footer\">Barcodes use the 200–299 in-store prefix range, so they never match a real retail product. Prices in EUR are illustrative.</footer>\n");
        h.append(Files.readString(Path.of("tools", "catalog-template-script.html")));

        Path target = dir.resolve("catalog.html");
        Files.writeString(target, h.toString(), StandardCharsets.UTF_8);
        System.out.println("Wrote " + target + " (" + products.size() + " products, " + brands.size()
                + " brands, " + byCategory.size() + " categories, languages: " + String.join("/", LANGS) + ")");
    }

    // ---------- flags (simplified, drawn in SVG) ----------
    static final String FLAG_GB = "<svg viewBox=\"0 0 60 30\" aria-hidden=\"true\"><clipPath id=\"gb-clip\"><path d=\"M30,15h30v15zv15h-30zh-30v-15zv-15h30z\"/></clipPath>"
            + "<rect width=\"60\" height=\"30\" fill=\"#012169\"/><path d=\"M0,0L60,30M60,0L0,30\" stroke=\"#fff\" stroke-width=\"6\"/>"
            + "<path d=\"M0,0L60,30M60,0L0,30\" clip-path=\"url(#gb-clip)\" stroke=\"#C8102E\" stroke-width=\"4\"/>"
            + "<path d=\"M30,0v30M0,15h60\" stroke=\"#fff\" stroke-width=\"10\"/><path d=\"M30,0v30M0,15h60\" stroke=\"#C8102E\" stroke-width=\"6\"/></svg>";
    static final String FLAG_DE = "<svg viewBox=\"0 0 5 3\" aria-hidden=\"true\"><rect width=\"5\" height=\"1\" fill=\"#000\"/>"
            + "<rect y=\"1\" width=\"5\" height=\"1\" fill=\"#DD0000\"/><rect y=\"2\" width=\"5\" height=\"1\" fill=\"#FFCE00\"/></svg>";
    static final String FLAG_TR = "<svg viewBox=\"0 0 1200 800\" aria-hidden=\"true\"><rect width=\"1200\" height=\"800\" fill=\"#E30A17\"/>"
            + "<circle cx=\"425\" cy=\"400\" r=\"200\" fill=\"#fff\"/><circle cx=\"475\" cy=\"400\" r=\"160\" fill=\"#E30A17\"/>"
            + "<polygon fill=\"#fff\" points=\"583.3,400 652.4,377.5 652.4,304.9 695.1,363.7 764.2,341.2 721.5,400 764.2,458.8 695.1,436.3 652.4,495.1 652.4,422.5\"/></svg>";

    static String langButton(String code, String label, String flag) {
        return "<button type=\"button\" data-lang=\"" + code + "\" aria-pressed=\"" + code.equals("en") + "\" title=\"" + label + "\">"
                + "<span class=\"flag\">" + flag.replace("<svg ", "<svg preserveAspectRatio=\"xMidYMid slice\" ") + "</span>" + code.toUpperCase(Locale.ROOT) + "</button>";
    }

    static String channelCard(String key, String name, String logo, String icon) {
        return "<button type=\"button\" class=\"channel-card\" data-channel=\"" + key + "\" data-name=\"" + name
                + "\" data-icon=\"" + icon + "\"><img src=\"" + logo + "\" alt=\"" + name + "\" width=\"300\" height=\"80\">"
                + "<span class=\"enter\" data-i18n=\"enter\">Enter</span></button>";
    }

    static String i18nAttrs(String en, String de, String tr) {
        return " data-en=\"" + esc(en) + "\" data-de=\"" + esc(de) + "\" data-tr=\"" + esc(tr) + "\"";
    }

    static String shortName(String name, String brand) {
        return name.startsWith(brand + " ") ? name.substring(brand.length() + 1) : name;
    }

    static int qty(Map<String, String> p) { return Integer.parseInt(p.get("stock_qty")); }

    static String stat(long n, String key, String label) {
        return "<div><b>" + n + "</b><span data-i18n=\"" + key + "\">" + label + "</span></div>";
    }

    static String stockTag(int qty) {
        if (qty == 0) return "<span class=\"tag out\" data-stock=\"0\">Out of stock</span>";
        if (qty < LOW_STOCK_LIMIT) return "<span class=\"tag low\" data-stock=\"" + qty + "\">Low stock · " + qty + "</span>";
        return "<span class=\"tag ok\" data-stock=\"" + qty + "\">In stock · " + qty + "</span>";
    }

    static String svgDataUri(Path svg) throws Exception {
        return "data:image/svg+xml;base64," + Base64.getEncoder().encodeToString(Files.readAllBytes(svg));
    }

    static String esc(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    /** Reads a JSON array of flat objects with string values (enough for brands.json and categories.json). */
    static List<Map<String, String>> readJsonObjects(Path file) throws Exception {
        List<Map<String, String>> out = new ArrayList<>();
        Matcher obj = Pattern.compile("\\{[^{}]*\\}").matcher(Files.readString(file, StandardCharsets.UTF_8));
        Pattern kv = Pattern.compile("\"([^\"]+)\"\\s*:\\s*\"([^\"]*)\"");
        while (obj.find()) {
            Map<String, String> m = new HashMap<>();
            Matcher f = kv.matcher(obj.group());
            while (f.find()) m.put(f.group(1), f.group(2));
            out.add(m);
        }
        return out;
    }

    static List<Map<String, String>> readCsv(Path file) throws Exception {
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        List<String> header = splitCsv(lines.get(0));
        List<Map<String, String>> out = new ArrayList<>();
        for (String line : lines.subList(1, lines.size())) {
            if (line.isBlank()) continue;
            List<String> f = splitCsv(line);
            if (f.size() != header.size()) throw new IllegalStateException("Wrong column count: " + line);
            Map<String, String> row = new HashMap<>();
            for (int i = 0; i < header.size(); i++) row.put(header.get(i), f.get(i));
            out.add(row);
        }
        return out;
    }

    static List<String> splitCsv(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean quoted = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (quoted && i + 1 < line.length() && line.charAt(i + 1) == '"') { cur.append('"'); i++; }
                else quoted = !quoted;
            } else if (c == ',' && !quoted) { out.add(cur.toString()); cur.setLength(0); }
            else cur.append(c);
        }
        out.add(cur.toString());
        return out;
    }
}
