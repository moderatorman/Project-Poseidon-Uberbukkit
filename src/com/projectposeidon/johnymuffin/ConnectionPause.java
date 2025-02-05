package com.projectposeidon.johnymuffin;

public class ConnectionPause {
    private String pluginName;
    private String connectionPauseName;
    private LoginProcessHandler loginProcessHandler;
    private long creationTime;
    private boolean active;

    public ConnectionPause(String PluginName, String connectionPauseName, LoginProcessHandler loginProcessHandler) {
        this.pluginName = PluginName;
        this.connectionPauseName = connectionPauseName;
        this.loginProcessHandler = loginProcessHandler;
        this.creationTime = System.currentTimeMillis();
        this.active = true;
    }

    /**
     * This method is still undecided, please don't use it in production.
     */
    public void removeConnectionPause() {
        loginProcessHandler.removeConnectionPause(this);
    }

    public String getPluginName() {
        return this.pluginName;
    }

    public String getConnectionPauseName() {
        return this.connectionPauseName;
    }


    public long getCreationTime() {
        return creationTime;
    }

    public boolean isActive() {
        return active;
    }

    /**
     * This method is for Poseidon, not plugin use. DON'T TOUCH THIS IF YOU DON'T KNOW WHAT YOU ARE DOING.
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}
