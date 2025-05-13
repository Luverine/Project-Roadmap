package Java.Bigginer.GitHubUserActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class GitHubUserActivity {
    // Main Method
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: Java GitHubUserActivity <username>");
            return;
        }

        String username = args[0];
        String apiURL = "https://api.github.com/users/" + username + "/events";

        try {
            URI uri = URI.create(apiURL);
            URL url = uri.toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/50");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error fetching data. HTTP response code: "+ responseCode);
                return;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) responseBuilder.append(inputLine);
            in.close();

            String jsonResponse = responseBuilder.toString();
            System.out.println("Fetched GitHub Activity: ");
            processEvents(jsonResponse);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

    // Extract Field
    private static String extractField(String text, String startDelimiter, String endDelimiter) {
        if (text == null) return null;

        int startIndex = text.indexOf(startDelimiter);
        if (startIndex == -1) return null;
        startIndex += startDelimiter.length();

        int endIndex = text.indexOf(endDelimiter, startIndex);
        if (endIndex == -1) return null;
        return text.substring(startIndex, endIndex);
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // Process Event
    private static void processEvents(String json) {
        if (json == null || json.isEmpty()) {
            System.out.println("No activity found");
            return;
        }

        if (json.startsWith("[") && json.endsWith("]")) json = json.substring(1, json.length() -1);

        String[] events = json.split("},\\{");

        for (String event : events) {
            if (!event.startsWith("{")) event = "{" +event;
            if (!event.endsWith("}")) event += "}";

            String type = extractField(event, "\"type\":\"", "\"");
            String repoBlock = extractField(event, "\"repo\":{", "}");
            if (repoBlock == null) continue;
            String repoName = extractField(repoBlock, "\"name\":\"", "\"");
            if (type == null || repoName == null) continue;

            String output;
            switch (type) {
                case "PushEvent":
                    String sizeStr = extractField(event, "\"size\":", ",");
                    int size = 0;
                    try {
                        size = Integer.parseInt(sizeStr.trim());
                    } catch (Exception ignored) {}
                    output = "Pushed " + size + " commit" + (size == 1 ? "" : "s") + " to " + repoName;
                    break;
                case "IssuesEvent":
                    String action = extractField(event, "\"action\":\"", "\"");
                    output = capitalize(action) + " a new issue in " + repoName;
                    break;

                case "CreateEvent":
                    String refType = extractField(event, "\"ref_type\":\"", "\"");
                    output = "Created a " + refType + " in " + repoName;
                    break;

                case "WatchEvent":
                    output = "Starred " + repoName;
                    break;

                default:
                    output = type + " in " + repoName;
                    break;
            }
            System.out.println("- "+output);
        }
    }
}
