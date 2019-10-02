<html>
	<body>
		<form action="${teamcityPluginResourcesPath}properties.html" id="agentPropertiesForm" method="post" autocomplete="off">
			<p><input class="btn btn_mini" type="submit" name="updateAgentPropertiesFile" value="Update Build Agent Properties"></p>
			 <input type="hidden" id="agent" name="agent" value="${agentId}">
	    	<textarea name="agentPropertiesFile" id="displayPropertiesFile" spellcheck="false" rows="30" cols="130" style="white-space: pre; overflow: auto; resize: none;">${agentProperties}</textarea>
	    </form>
    </body>
</html>
