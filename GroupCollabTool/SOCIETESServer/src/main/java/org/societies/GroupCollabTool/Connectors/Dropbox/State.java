package org.societies.GroupCollabTool.Connectors.Dropbox;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dropbox.client2.jsonextract.JsonExtractionException;
import com.dropbox.client2.jsonextract.JsonList;
import com.dropbox.client2.jsonextract.JsonMap;
import com.dropbox.client2.jsonextract.JsonThing;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;


	 // ------------------------------------------------------------------------
    // State model (load+save to JSON)

    public class State
    {

        public final AppKeyPair appKey;
        public final Map<String,AccessTokenPair> links = new HashMap<String,AccessTokenPair>();
        public String cursor;
        
        public State(AppKeyPair appKey, String cursor)
        {
            this.appKey = appKey;
            this.cursor = cursor;
        }

        public void save(String fileName) throws Exception
        {
            JSONObject jstate = new JSONObject();

            // Convert app key
            JSONArray japp = new JSONArray();
            japp.add(appKey.key);
            japp.add(appKey.secret);
            jstate.put("app_key", japp);
            
            jstate.put("cursor", cursor);

            // Convert 'Link' objects (uid -> access token)
            JSONObject jlinks = new JSONObject();
            for (Map.Entry<String,AccessTokenPair> link : links.entrySet()) {
                String uid = link.getKey();
                AccessTokenPair access = link.getValue();
                JSONArray jaccess = new JSONArray();
                jaccess.add(access.key);
                jaccess.add(access.secret);
                jlinks.put(uid, jaccess);
            }
            jstate.put("links", jlinks);

            try {
                FileWriter fout = new FileWriter(fileName);
                try {
                    jstate.writeJSONString(fout);
                }
                finally {
                    fout.close();
                }
            }
            catch (IOException ex) {
                throw new Exception("ERROR: unable to save to state file \"" + fileName + "\": " + ex.getMessage());
            }
        }

        public static State load(String fileName) throws Exception
        {
            JsonThing j;
            try {
                FileReader fin = new FileReader(fileName);
                try {
                    j = new JsonThing(new JSONParser().parse(fin));
                } catch (ParseException ex) {
                    throw new Exception("ERROR: State file \"" + fileName + "\" isn't valid JSON: " + ex.getMessage());
                } finally {
                    fin.close();
                }
            }
            catch (IOException ex) {
                throw new Exception("ERROR: unable to load state file \"" + fileName + "\": " + ex.getMessage());
            }

            try {
                JsonMap jm = j.expectMap();

                JsonList japp = jm.get("app_key").expectList();
                AppKeyPair appKey = new AppKeyPair(japp.get(0).expectString(), japp.get(1).expectString());
                ;
                
                State state = new State(appKey,jm.get("cursor").expectStringOrNull());

                JsonMap jlinks = jm.get("links").expectMap();
                for (Map.Entry<String,JsonThing> jlink : jlinks) {
                    JsonList jaccess = jlink.getValue().expectList();
                    AccessTokenPair access = new AccessTokenPair(jaccess.get(0).expectString(), jaccess.get(1).expectString());
                    state.links.put(jlink.getKey(), access);
                }

                return state;
            }
            catch (JsonExtractionException ex) {
                throw new Exception ("ERROR: State file has incorrect structure: " + ex.getMessage());
            }
        }
    }

   