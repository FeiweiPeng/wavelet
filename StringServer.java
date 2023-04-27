import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String result = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return result;
        } 
        else if (url.getPath().equals("/add-message")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s") == true){
                if(result.length() == 0){result = result + parameters[1];}
                else{result = result + "\n" + parameters[1];}
                return result;
            }
            else{return "404 Not Found!";}
        } 
        else if(url.getPath().equals("/search")){
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s") == true){
                String searched = "";
                String[] list = result.split("\n");
                for(int i = 0; i < list.length;i++){
                    if(list[i].contains(parameters[1])){
                        if(searched.length() == 0){searched += list[i];}
                        else{searched = searched + "\n" + list[i];}
                    }
                }
                return searched;
            }
            else{
                return "404 Not Found!";
            }
        }
        else {
            return "404 Not Found!";
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
