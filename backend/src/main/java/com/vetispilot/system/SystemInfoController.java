package com.vetispilot.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Smallest possible endpoint: proves that the backend is running and
 * tells the frontend which languages and channels exist.
 *
 * GET /api/v1/system/info
 */
@RestController
@RequestMapping("/api/v1/system")
public class SystemInfoController {

    private final String version;

    public SystemInfoController(@Value("${vetispilot.version}") String version) {
        this.version = version;
    }

    @GetMapping("/info")
    public SystemInfo info() {
        return new SystemInfo("VetisPilot", version, List.of("en", "de", "tr"), List.of("HTGO", "GK"));
    }

    /** The JSON answer. A record is a small, unchangeable data class. */
    public record SystemInfo(String name, String version, List<String> languages, List<String> channels) {
    }
}
