package com.example.demosearch;

import java.util.List;

public class responseObject{

    private List<item> results;
    private item it;
    private String updated;
    private String term;
    private Integer status_code;
    private String variant;

    public responseObject(List<item> res, item itt, String update, String ter, Integer sc, String var){
        this.results = res;
        this.it = itt;
        this.updated = update;
        this.term = ter;
        this.status_code = sc;
        this.variant = var;
    }

    public List<item> getResult(){
        return this.results;
    }

    public void setResult(List<item> res){
        this.results = res;
    }

    public item getIt(){
        return this.it;
    }

    public void setIt(item it){
        this.it = it;
    }

    public String getUpdated(){
        return this.updated;
    }

    public void setUpdated(String up){
        this.updated = up;
    }

    public String getTerm(){
        return this.term;
    }

    public void setTerm(String term){
        this.term = term;
    }

    public Integer getStatus_code(){
        return this.status_code;
    }

    public void setStatus_code(Integer s){
        this.status_code = s;
    }

    public String getVariant(){
        return this.variant;
    }

    public static class item{
        private String id;
        private String picture;
        private String name;
        private List<locations> locations;
        private String provider;
        private Integer weight;

        public item(String id, String pic, String name, List<locations> location, String provider, Integer weight){
            this.id = id;
            this.picture = pic;
            this.name = name;
            this.locations = location;
            this.provider = provider;
            this.weight = weight;
        }
    //useless rn
        public String toString(){
            String res;
            res = "name:"+this.name+"\n";
            res += "picture: "+this.picture+"\n";
            res += "locations: ";
            for(locations l : locations){
                res += l+"\n";
            }

            return res;
        }

        public String getId(){
            return this.id;
        }

        public void setId(String i){
            this.id = i;
        }

        public String getPic(){
            return this.picture;
        }

        public void setPic(String p){
            this.picture = p;
        }

        public String getName(){
            return this.name;
        }

        public void setName(String n){
            this.name = n;
        }

        public List<locations> getLocation(){
            return this.locations;
        }

        public void setLocation(List<locations> l){
            this.locations = l;
        }

        public String getProvider(){
            return this.provider;
        }

        public void setProvider(String p){
            this.provider = p;
        }

        public Integer getWeight(){
            return this.weight;
        }

        public void setWeight(Integer w){
            this.weight = w;
        }

        public static class locations{
            private String icon;
            private String display_name;
            private String name;
            private String id;
            private String uri;

            public locations(String icon, String display_name, String name, String id, String uri){
                this.icon = icon;
                this.display_name = display_name;
                this.name = name;
                this.id = id;
                this.uri = uri;
            }

            public String getIcon(){
                return this.icon;
            }

            public void setIcon(String i){
                this.icon = i;
            }

            public String getDisplay_name(){
                return this.display_name;
            }

            public void setDisplay_name(String d){
                this.display_name = display_name;
            }

            public String getName(){
                return this.name;
            }

            public void setName(String n){
                this.name = n;
            }

            public String getId(){
                return this.id;
            }

            public void setId(String i){
                this.id=i;
            }

            public String getUri(){
                return this.uri;
            }

            public void setUri(String u){
                this.uri = u;
            }

        }
    }



       /* {"results":
            [{
                "id": "5d97da029a76a40056de1c59",
                "picture": "https://utellyassets9-1.imgix.net/api/Images/2b54cb3bdb54d7bb696801e52a789247/Redirect",
                "name": "Nella the Princess Knight",
                "locations":
                    [{
                        "icon": "https://utellyassets7.imgix.net/locations_icons/utelly/black_new/GooglePlayIVACA.png?w=92&auto=compress&app_version=8bc263d1-dd7b-40c0-98cd-f677eb14d81e_e12122020-10-27",
                        "display_name": "Google Play",
                        "name": "GooglePlayIVACA",
                        "id": "5d84d6ddd95dc7385f6a43eb",
                        "url": "https://play.google.com/store/tv/show?amp=&amp=&cdid=tvseason-L2LpMv5jWB9shr1ABBPEGg&gdid=tvepisode-UFsxdW-kaMw&gl=CA&hl=en&id=RmS_3uRtDJmG6knRcnyCOQ"}],
                "provider": "iva",
                "weight": 7733,
                "external_ids":
                    {
                        "iva_rating": null,
                        "imdb":
                            {"url": "https://www.imdb.com/title/tt6415656",
                            "id": "tt6415656"},
                        "tmdb":
                            {"url": "https://www.themoviedb.org/tv/70104",
                                    "id": "70104"},
                            "wiki_data":
                                {"url": "https://www.wikidata.org/wiki/Q28312035",
                                        "id": "Q28312035"},
                            "iva": {"id": "404751"},
                            "gracenote": null,
                            "rotten_tomatoes": null,
                            "facebook": null}},
            {
                "id": "5ed7b803ba30cfc1910f143d",
                "picture": "https://utellyassets9-1.imgix.net/api/Images/1c580fc0c52f42c2e50fe57ce8199177/Redirect",
                "name": "Lego Jurassic World: Legend of Isla Nublar",
                "locations":
                    [{
                        "icon": "https://utellyassets7.imgix.net/locations_icons/utelly/black_new/iTunesIVACA.png?w=92&auto=compress&app_version=8bc263d1-dd7b-40c0-98cd-f677eb14d81e_e12122020-10-27",
                        "display_name": "iTunes",
                        "name": "iTunesIVACA",
                        "id": "5d8415b32393e90053ac366c",
                        "url": "https://itunes.apple.com/ca/tv-season/under-the-volcano/id1500308383?i=1502262476"},
                    {
                        "icon": "https://utellyassets7.imgix.net/locations_icons/utelly/black_new/GooglePlayIVACA.png?w=92&auto=compress&app_version=8bc263d1-dd7b-40c0-98cd-f677eb14d81e_e12122020-10-27",
                            "display_name": "Google Play",
                            "name": "GooglePlayIVACA",
                            "id": "5d84d6ddd95dc7385f6a43eb",
                            "url": "https://play.google.com/store/tv/show?amp=&amp=&cdid=tvseason-JdO_Dum-Z9Am21K4gn9eaw&gdid=tvepisode-XRkYW_8asYU&gl=CA&hl=en&id=pjUC4n_Gb94361bxBgOwhw"}],
                "provider": "iva",
                "weight": 0,
                "external_ids":
                    {
                        "iva_rating": null,
                        "imdb":
                            {
                                "url": "https://www.imdb.com/title/tt10872880",
                                "id": "tt10872880"},
                        "tmdb": {"url": "https://www.themoviedb.org/tv/93895", "id": "93895"},
                        "wiki_data": {"url": "https://www.wikidata.org/wiki/Q76574623", "id": "Q76574623"},
                        "iva": {"id": "676719"}, "gracenote": null, "rotten_tomatoes": null, "facebook": null}}],
            "updated": "2020-10-27T05:19:14+0000",
            "term": "null",
            "status_code": 200,
            "variant": "ivafull"}*/
}