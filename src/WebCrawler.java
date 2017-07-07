package bfs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This is a very rudimentary WebCrawler
public class WebCrawler {
	private Queue<String> queue;
	private List<String> discoveredSites;

	public WebCrawler() {
		queue = new LinkedList<String>();
		discoveredSites = new ArrayList<String>();
	}

	public void crawl(String url) throws IOException{
		queue.add(url);
		discoveredSites.add(url);
		while(!queue.isEmpty()){
			String currURL = queue.remove();
			String rawHTML = readURL(currURL);
			String regex ="http(s)?://(\\w+)\\.(\\w+)"; 
			// http[s]://<alphanumeric>dot<alphanumeric>
			// note \w+ = any alphanumeric word. Extra backshlash for Java String

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(rawHTML);

			while (matcher.find()){
				String matchedURL = matcher.group();
				if (!discoveredSites.contains(matchedURL)){
					System.out.println("Discovered :" + matchedURL);
					queue.add(matchedURL);
					discoveredSites.add(matchedURL);
				}
			}
		}
	}

	private String readURL(String currURL) {
		StringBuffer sb = null;
		try {
			URL url = new URL(currURL);
			sb = new StringBuffer();
			try (BufferedReader bir = new BufferedReader(new InputStreamReader(url.openStream()))){
				String line = "";
				while ((line = bir.readLine()) != null){
					sb.append(line);
				}
			}
		} catch (MalformedURLException e) {
			System.out.println(" For " + currURL + " exception " + e.getMessage());
		} catch (IOException e) {
			System.out.println(" For " + currURL + " exception " + e.getMessage());
		}
		return sb.toString();
	}
	public List<String> getDiscoveredSites() {
		return discoveredSites;
	}
	
	public static void main(String[] args) throws IOException {
		WebCrawler wc = new WebCrawler();
		System.out.println("Crawling started...");
		wc.crawl("https://github.com/navspeak");
		System.out.println("Discovered sites : " + wc.getDiscoveredSites().size());

	}
}
