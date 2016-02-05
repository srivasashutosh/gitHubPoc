package githubpoc

import java.sql.Connection;
import org.apache.commons.codec.binary.Base64;

import grails.transaction.Transactional
import groovy.json.JsonSlurper;

@Transactional
class GitHubApiService {

	def serviceMethod() {
	}


	def totalOpenIssuesCount(def url, def username, def password){

		def encodedCredentials = encodecredentials(username, password);
		println "encodedCredetials are ********"+encodedCredentials
		def apiUrl = url + "/issues?status=open"
		HttpURLConnection connection = createConnection(apiUrl, encodedCredentials)
		def parsedResult = parseResponse(connection.getInputStream());
		int total=0
		parsedResult.each{issue->
			total++
		}
		return total
	}

	def openIssuesWithin1DayCount(def url, def username, def password){
		
		def encodedCredentials = encodecredentials(username, password);
		def time = new Date(new Date().time -24*60*60*1000).format("YYYY-MM-dd'T'HH:mm:ss'Z'")
		def apiUrl = url + "/issues?status=open&since="+time 
		HttpURLConnection connection = createConnection(apiUrl, encodedCredentials)
		def parsedResult = parseResponse(connection.getInputStream());
		int total=0
		parsedResult.each{issue->
			total++
		}
		return total
	}
	def openIssuesBeforeSevenDays(def url, def username, def password){
		def encodedCredentials = encodecredentials(username, password);
		def apiUrl = url + "/issues"
		HttpURLConnection connection = createConnection(apiUrl, encodedCredentials)
		def parsedResult = parseResponse(connection.getInputStream());
		int total =0
		parsedResult.each{issue->
			def time = new Date(new Date().time -7*24*60*60*1000).format("YYYY-MM-dd'T'HH:mm:ss'Z'")
			println "total open issues "+issue.state
			if(issue.created_at < time){
				total++
				
			}
		}
		return total
	}
	
	def openIssuesBetweenSevenAndLastDays(def url, def username, def password){
		def encodedCredentials = encodecredentials(username, password);
		def lastSeventhDay = new Date(new Date().time -7*24*60*60*1000).format("YYYY-MM-dd'T'HH:mm:ss'Z'")
		def apiUrl = url + "/issues?status=open&since="+lastSeventhDay 
		HttpURLConnection connection = createConnection(apiUrl, encodedCredentials)
		def parsedResult = parseResponse(connection.getInputStream());
		int total =0
		parsedResult.each{issue->
			
			def lastDay = new Date(new Date().time -24*60*60*1000).format("YYYY-MM-dd'T'HH:mm:ss'Z'")
			if(issue.created_at < lastDay){
				total++
				
			}
		}
		return total
	}
	
	//creating connection for get request
	def createConnection(def Url , def authString){


		URL url = new URL(Url);


		HttpURLConnection connection = url.openConnection();

		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type", "text/plain");
		connection.setRequestProperty("charset", "UTF-8");
		connection.setRequestProperty("Authorization", "Basic "+authString);


		def responseCode = connection.getResponseCode()

		println "status code is ***********"+responseCode

		return connection
	}

	def encodecredentials(String user, String pass){

		String authString = user + ":"+pass

		String authStringenc = new String(Base64.encodeBase64(org.apache.commons.codec.binary.StringUtils.getBytesUtf8(authString)));
		return authStringenc
	}
	
	
	def parseResponse(InputStream inputStream){
		
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
		def jsonSlurper = new JsonSlurper();
		def parsedResult = jsonSlurper.parseText(br.text)
		return parsedResult;
		
		
		
	}
	
	def modifyGitHubUrl(String gitHubUrl){
		
		//gitHubUrl = "https://github.com/Shippable/support/issues"
		
		def val = gitHubUrl.split("/")
		
		def account = val[3]
		def repo = val[4]
		def newUrl = "https://api.github.com/repos/"+account+"/"+repo
		return newUrl;
		
		
	}
}
