plugins {
    id("de.chkpnt.truststorebuilder") version "0.6.0"
}


trustStoreBuilder {

    // Configures and registers the tasks `buildTrustStore` and `checkCertificates`.
    trustStore {
        // Path pointing to the TrustStore being built. Can be anything that can be handled by `project.file(...)`.
        // The type of the TrustStore is derived from the file extension. Supported are jks, p12, and pfx.
        // Defaults to '$buildDir/cacerts.jks'
        //path("$buildDir/cacerts.jks")

        // The password used for the TrustStore. Defaults to 'changeit'.
        //password("changeit")

        // The directory which is scanned for certificates, which is resolved using `project.file(...)`.
        // Defaults to '$projectDir/src/main/certs'.
        //source("src/main/certs")

        // Filter for the source directory. Defaults to ['**/*.crt', '**/*.cer', '**/*.pem'].
        //include("**/*.crt", "**/*.cer", "**/*.pem")

        // Should the build-task depend on buildTrustStore? Defaults to true.
        //buildEnabled.set(true)
    }

    // The tasks can be named as well, so it is possible to build multiple TrustStores with
    // different configurations.
    // Here a PKCS12-container is built as a second TrustStore:
    trustStore("pkcs12") {
        path("$buildDir/cacerts.p12")
    }

    checkCertificates {
        // The directory which is scanned for certificates, which is resolved using `project.file(...)`.
        // Defaults to '$projectDir/src/main/certs'.
        //source("src/main/certs")

        // Filter for the source directory. Defaults to ['**/*.crt', '**/*.cer', '**/*.pem'].
        //include("**/*.crt", "**/*.cer", "**/*.pem")

        // Exclusions for the source directory, can be called multiple times. Defaults to [].
        exclude("Mozilla TrustStore/cacert.pem") // contains expired certs

        // Number of days the certificates have to be at least valid. Defaults to 90 days.
        //atLeastValidDays.set(90)

        // Should the check-task depend on checkCertificates? Defaults to true.
        //checkEnabled.set(true)
    }

}
