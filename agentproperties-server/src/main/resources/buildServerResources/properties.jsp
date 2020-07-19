<html>
	<body>
		<form action="${teamcityPluginResourcesPath}properties.html" id="agentPropertiesForm" method="post" autocomplete="off">
			<p><input class="btn btn_mini" type="submit" name="updateAgentPropertiesFile" value="Update Build Agent Properties"></p>
			 <input type="hidden" id="agent" name="agent" value="${agentId}">
			 <input type="hidden" id="agent-props-csrf" name="tc-csrf-token">
	    	<textarea name="agentPropertiesFile" id="displayPropertiesFile" spellcheck="false" rows="30" cols="130" style="white-space: pre; overflow: auto; resize: none;">${agentProperties}</textarea>
	    </form>
    </body>
</html>

<script type="text/javascript">
  var token = $j('meta[name=tc-csrf-token]').attr('content');
  if(token) {
    $('agent-props-csrf').value = token;
  }
</script>