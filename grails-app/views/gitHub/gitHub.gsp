<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
<asset:stylesheet src="bootstrap.min.css" />
<asset:stylesheet src="bootstrap-theme.min.css" />
<asset:stylesheet src="font-awesome.css" />
<asset:javascript src="jquery.min.js" />
<asset:javascript src="bootstrap.min.js" />
<asset:stylesheet src="multi-select.css" />
<asset:javascript src="jquery.multi-select.js" />
<script type="text/javascript">



function getIssues(){

	var gitHubUrl = $("#githuburl").val();
	var encodedUrl = encodeURIComponent(gitHubUrl);
	
$.ajax({


	url: "${createLink(controller:"gitHubApi",action:"openIssueCount")}",
	
	type:'post',
	data : {"githuburl":gitHubUrl},
	success: function(data){

		
		$("#openIssueLabel").show();
		$("#openIssue").empty();		
		$("#openIssue").append(data);	

		},

		error:function(jqXHR,responseText){

          alert(responseText)

			}


});

$.ajax({


	url: "${createLink(controller:"gitHubApi",action:"openIssueLastDay")}",
	
	type:'post',
	data : {"githuburl":gitHubUrl},
	success: function(data){

		$("#openIssueLast1Label").show();		
		$("#openIssueLast1").empty();	
		$("#openIssueLast1").append(data);	

		},

		error:function(jqXHR,responseText){

          alert(responseText)

			}


});

$.ajax({


	url: "${createLink(controller:"gitHubApi",action:"openIssueBeforeSevenDay")}",
	
	type:'post',
	data : {"githuburl":gitHubUrl},
	success: function(data){

		$("#openIssueBefore7Label").show();		
		$("#openIssueBefore7").empty();			
		$("#openIssueBefore7").append(data);	

		},

		error:function(jqXHR,responseText){

          alert(responseText)

			}


});

$.ajax({


	url: "${createLink(controller:"gitHubApi",action:"openIssueBetweenLastAndSevenDay")}",
	
	type:'post',
	data : {"githuburl":gitHubUrl},
	success: function(data){

		$("#openIssueBetween1And7Label").show();		
		$("#openIssueBetween1And7").empty();		
		$("#openIssueBetween1And7").append(data);	

		},

		error:function(jqXHR,responseText){

          alert(responseText)

			}


});

}
</script>
</head>
<body>
	<div style="margin-top: 3em;">
		<div class="row" >
			<div class="col-md-4 col-sm-4"></div>
			<div class="col-md-4 col-sm-4">
				<div class="form-group">
					<label for="githuburl" class="col-md-4 control-label">
						GitHub Url</label>
					<div class="col-md-12 col-md-12">
						<input type="text" class="form-control" id="githuburl">
					</div>
				</div>
				
				<div class="form-group">
				<div class="col-md-offset-4 col-md-4" style="margin-top: 1em;" >
					<button type="button" class="btn btn-primary btn-block" onclick="getIssues()">Submit
						</button>
				</div>
			</div>
			</div>
			<div class="col-md-4 col-sm-4"></div>
		</div>
		<div class="row" style="margin-left: 40px;">
			<div class="col-md-3 col-sm-3">
			<label id="openIssueLabel" for="openIssue" class="col-md-6 control-label" style="display: none;">
						Total Open Isuues</label>
			<div id="openIssue"></div> 
			</div>
			<div class="col-md-3 col-sm-3">
			<label id="openIssueLast1Label" for="openIssue" class="col-md-6 control-label" style="display: none;">
						Open Issues In Last 24 Hr</label>
			<div id="openIssueLast1"></div> 
			</div>
			<div class="col-md-3 col-sm-3">
			<label id="openIssueBefore7Label" for="openIssue" class="col-md-6 control-label" style="display: none;">
						Open Issues Before 7 Days </label>
			<div id="openIssueBefore7"></div> 
			</div>
			<div class="col-md-3 col-sm-3">
			<label id="openIssueBetween1And7Label" for="openIssue" class="col-md-6 control-label" style="display: none;">
						Open Issues After 1 Day but before 7 Days</label>
			<div id="openIssueBetween1And7"></div> 
			</div>
		</div>
	</div>
</body>

</html>
