package org.societies.GroupCollabTool.Connectors.Dropbox;

import com.dropbox.client2.session.AccessTokenPair;


 public final class Link
    {
        public final String uid;
        public final AccessTokenPair accessToken;

        public Link(String uid, AccessTokenPair accessToken)
        {
            this.uid = uid;
            this.accessToken = accessToken;
        }
    }