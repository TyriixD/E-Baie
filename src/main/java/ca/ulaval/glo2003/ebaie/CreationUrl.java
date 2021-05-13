package ca.ulaval.glo2003.ebaie;

public class CreationUrl {

    private Integer port;
    private String hostname;
    private String headerHostName;

    public String getHeaderHostName() {
        return headerHostName;
    }

    public CreationUrl() {
    }

    public Integer getPort() {
        return port;
    }

    public String getHostname() {
        return hostname;
    }

    private Integer getPortDeploy() {
        return Integer.valueOf(System.getenv("PORT"));
    }

    public void setCreationUrlDefault() {
        port = 8080;
        hostname = "localhost";
        headerHostName = "http://localhost:8080/api";
    }


    public void setCreationUrlStaging() {
        port = getPortDeploy();
        hostname = "0.0.0.0";
        headerHostName = "https://quiet-meadow-01980-staging.herokuapp.com/api";
    }

    public void setCreationUrlProduction() {
        port = getPortDeploy();
        hostname = "0.0.0.0";
        headerHostName = "https://sheltered-hamlet-76818-prod.herokuapp.com/api";
    }

}
