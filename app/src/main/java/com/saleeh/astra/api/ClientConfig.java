package com.saleeh.astra.api;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * @author KeithYokoma
 */
public class ClientConfig {
    private final String mClientId;
    private final String mClientSecret;
    private final String mUrl;

    /* package */ ClientConfig(@NonNull String clientId, @NonNull String clientSecret, @NonNull String Url) {
        mClientId = clientId;
        mClientSecret = clientSecret;
        mUrl = Url;
    }

    public @NonNull
    String getClientId() {
        return mClientId;
    }

    public @NonNull
    String getClientSecret() {
        return mClientSecret;
    }

    public @NonNull
    String getUrl() {
        return mUrl;
    }

    public static class Builder {
        private String mClientId;
        private String mClientSecret;
        private String mUrl;

        public Builder() {}

        public @NonNull
        Builder setClientId(@NonNull String clientId) {
            mClientId = clientId;
            return this;
        }

        public @NonNull
        Builder setClientSecret(@NonNull String clientSecret) {
            mClientSecret = clientSecret;
            return this;
        }

        public @NonNull
        Builder setUrl(@NonNull String url) {
            mUrl = url;
            return this;
        }

        public @NonNull
        ClientConfig build() {
            validate();
            return new ClientConfig(mClientId, mClientSecret, mUrl);
        }

        /* package */ void validate() {
            if (TextUtils.isEmpty(mClientId)) {
                throw new IllegalStateException("Client id may not be null or empty.");
            }
            if (TextUtils.isEmpty(mClientSecret)) {
                throw new IllegalStateException("Client secret may not be null or empty.");
            }
            if (TextUtils.isEmpty(mUrl)) {
                throw new IllegalStateException("URL may not be null or empty.");
            }
        }
    }
}
