import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    List<String> list = Arrays.asList("apple", "banana");

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Peng's Strings: %d", list);
        } 
        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("addone") == true){
                list.add(parameters[1]);
                return String.format("String added! Current Strings: %d", list);
            }
            else if(parameters[0].equals("sub")){
                List<String> search = Arrays.asList();
                for(int i = 0; i <list.size();i++){
                    if(list.get(i).contains(parameters[1])){
                        search.add(list.get(i));           
                    }
                }
                return String.format("Strings you searched are: %d", search);
            }
            else{return "404 Not Found!";}
        } 
        else {
            return "404 Not Found!";
        }
    }
}

class EngineServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
