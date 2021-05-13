package ca.ulaval.glo2003.ebaie;

import ca.ulaval.glo2003.ebaie.app.EBaieApplication;
import ca.ulaval.glo2003.ebaie.app.JettyJerseyApp;

import java.util.Optional;

@SuppressWarnings("all")
public class EBaieMain {

    public static void main(String[] args) throws Exception {
        String profile = Optional.ofNullable(System.getenv("PROFILE")).orElse("dev");
        CreationUrl url = new CreationUrl();
        if (profile.equals("staging")) {
            url.setCreationUrlStaging();
        } else if (profile.equals("production")) {
            url.setCreationUrlProduction();
        } else if (profile.equals("dev")) {
            url.setCreationUrlDefault();
        } else {
            throw new IllegalArgumentException("Unknown profile");
        }
        EBaieApplication app = new JettyJerseyApp(url, profile);
        app.start();
    }
}
