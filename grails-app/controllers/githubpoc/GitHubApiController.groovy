package githubpoc

import grails.converters.JSON

class GitHubApiController {

	
	def gitHubApiService;
    def index() { 
		
		render(view:"/gitHub/gitHub")
	}
	
	
	def openIssueCount(){
		
		def gitHubUrl = params.githuburl;
		println gitHubUrl
		def username = grailsApplication.config.credentials.username
		def password= grailsApplication.config.credentials.password
		
		println username + "*****" + password
		def apiUrl = gitHubApiService.modifyGitHubUrl(gitHubUrl)
		def issueCount = gitHubApiService.totalOpenIssuesCount(apiUrl,username,password);
		render(issueCount) as JSON
	}
	
	def openIssueLastDay(){
		
		def gitHubUrl = params.githuburl;
		def username = grailsApplication.config.credentials.username
		def password= grailsApplication.config.credentials.password
		def apiUrl = gitHubApiService.modifyGitHubUrl(gitHubUrl)
		def issueCount = gitHubApiService.openIssuesWithin1DayCount(apiUrl,username,password);
		render(issueCount) as JSON
	}
	
	def openIssueBeforeSevenDay(){
		
		def gitHubUrl = params.githuburl;
		def username = grailsApplication.config.credentials.username
		def password= grailsApplication.config.credentials.password
		def apiUrl = gitHubApiService.modifyGitHubUrl(gitHubUrl)
		def issueCount = gitHubApiService.openIssuesBeforeSevenDays(apiUrl,username,password);
		render(issueCount) as JSON
	}
	
	def openIssueBetweenLastAndSevenDay(){
		
		def gitHubUrl = params.githuburl;
		def username = grailsApplication.config.credentials.username
		def password= grailsApplication.config.credentials.password
		def apiUrl = gitHubApiService.modifyGitHubUrl(gitHubUrl)
		def issueCount = gitHubApiService.openIssuesBetweenSevenAndLastDays(apiUrl,username,password);
		render(issueCount) as JSON
	}
	
}
