import java.util.*;

public class Crew {
    private static Random random = new Random();
    private String firstName;
    private String lastName;
    private Module station;

    public Crew(){
        this.firstName = GetRandomName(firstNames);
        this.lastName = GetRandomName(lastNames);
    }

    public String getName(){
        return firstName+" "+lastName;
    }

    public void addToStation(Module m){
        if(m instanceof Weapon || m instanceof ShieldGenerator){
            this.station = m;
        }
    }

    public void removeFromStation(){
        this.station = null;
    }

    public Module getStation(){
        return station;
    }















    private static String GetRandomName(String[] names){
        return names[random.nextInt(names.length)];
    }

    private static String[] firstNames =
        {"Lisa","Ramon","Janet","Catherine","Chris","Wokje","Thuong","Andrea",
            "Manjeet","Toby","Philip","Bing","Renee","Derek","David","John",
            "Christian","Yongxin","Charles","Michael","Colin","Helen","Mansoor","Rod",
            "Todd","Dan","Colin","Shirley","Alex","John","Michael","Peter",
            "Paul","Ian","Jenny","Bob","Jeffrey","Joanna","Kathryn","Andy",
            "Inge","Maree","Rosie","Joanne","Yau","Rebecca","Robyn","Christine",
            "Guy","Christina","Tirta","Ruiping","Victoria","Bernadette","Catherine","Mo",
            "Tom","Natalie","Harold","Dimitrios","Alexander","James","Michael",
            "Yu-Wei","Emily","Christian","Alia","Zohar","Kimberly","Ocean","Yi",
            "Jamy","Travis","Deborah","Kim","Linda","Gillian","Bronwyn","Bruce",
            "Miriam","Gillian","Jenny"};

    private static String[] lastNames =
        {"Alcock","Ansell","Armstrong","Bai","Bates","Biddle","Bradley","Brunt","Calvert",
            "Chawner","Cho","Clark","Coxhead","Cullen","Daubs","Day","Dinica","Downey",
            "Dunbar","Elinoff","Fortune","Gabrakova","Geng","Goreham","Groves","Hall",
            "Harris","Hodis","Horgan","Hunt","Jackson","Jones","Keane-Tuala","Khaled",
            "Kidman","Krtalic","Laufer","Levi","Locke","Mackay","Marquez","Maskill",
            "Maxwell","McCrudden","McGuinness","McMillan","Mei","Millington","Moore",
            "Murphy","Nelson","Niemetz","O'Hare","Owen","Pearce","Perris","Pirini",
            "Pratt","Randal","Reilly","Rimoni","Robinson","Ruck","Schipper",
            "Servetto","Shuker","Skinner","Speedy","Stevens","Sweet","Taylor",
            "Terreni","Timperley","Turetsky","Vignaux","Wallace","Welch","Wilson",
            "Ackerley","Adds","Anderson","Anslow","Antunes","Armstrong","Arnedo-Gomez",
            "Bacharach","Bai","Barrett","Baskerville","Bennett","Berman","Boniface",
            "Boston","Brady","Bridgman","Brunt","Buettner","Calhoun","Calvert",
            "Capie","Carmel","Chiaroni","Chicca","Chu","Chu","Clark",
            "Clayton","Coxhead","Craig","Cuffe","Cullen","Dalli","Das",
            "Davidson","Davies","Desai","Devue","Dinneen","Dmochowski","Downey",
            "Doyle","Dumitrescu","Dunbar","Elgort","Elias","Faamanatu-Eteuati","Feld",
            "Fraser","Frean","Galvosas","Gamble","Geng","George","Goh",
            "Goreham","Gregory","Grener","Guy","Haggerty","Hammond","Hannah",
            "Harvey","Haywood","Hodis","Hogg","Horgan","Horgan","Hubbard",
            "Hui","Ingham","Jack","Johnston","Johnston","Jordan","Joyce",
            "Keane-Tuala","Kebbell","Keyzers","Khaled","Kiddle","Kiddle","Kirkby",
            "Knewstubb","Kuehne","Lacey","Leah","Leggott","Levi","Lindsay",
            "Loader","Locke","Lynch","Ma","Mallett","Mares","Marriott",
            "Marshall","Maslen","Mason","Maxwell","May","McCarthy","McCrudden",
            "McDonald","McGregor","McKee","McKinnon","McNeill","McRae","Mercier",
            "Metuarau","Millington","Mitsotakis","Molloy","Moore","Muaiava","Muckle",
            "Natali","Neha","Newton","Nguyen","Nisa","Noakes-Duncan",
            "Ok","Overton","Park","Parkinson","Penetito","Perkins","Petkov",
            "Pham","Pivac","Plank","Price","Raman","Rees","Reichenberger",
            "Riad","Rice-Davis","Ritchie","Robb","Rofe","Rook","Ruegg",
            "Schick","Scott","Seals","Sheffield","Shewan","Sim","Simpson",
            "Smaill","Smith","Spencer","Stern","Susilo","Sutherland","Tariquzzaman",
            "Tatum","Te Huia","Te Morenga","Thirkell-White","Thomas","Tokeley","Trundle",
            "Van Belle","Van Rij","Vowles","Vry","Ward","Warren","White",
            "Whittle","Wilson","Wilson","Wood","Yao","Yu","Zareei",
            "de Saxe","de Sylva","van der Meer", "Woods","Yates","Zhang","van Zijl"
        };
}
