package com.ks.exoplayer.ata.network.model;

import java.util.List;

public class ApiResponse {

    private String updatedAt;
    private List<ApiVideo>resources;

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ApiVideo> getResources() {
        return resources;
    }

    public void setResources(List<ApiVideo> resources) {
        this.resources = resources;
    }
}


