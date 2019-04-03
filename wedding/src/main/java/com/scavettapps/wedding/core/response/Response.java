package com.scavettapps.wedding.core.response;

import org.springframework.beans.factory.annotation.Value;

public abstract class Response {

    @Value("${leash.api.version}")
    private String version;

    /**
     * @return the version
     */
    public String getVersion() {
	return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
	this.version = version;
    }
}
