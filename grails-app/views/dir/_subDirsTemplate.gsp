<ul class="sub-dirs-list" >


	<div class="subdir-dropdown">

		<g:link action="show" id="${subDir.id}">${subDir.name}</g:link>
		<span>ᐁ</span>
		<li class="sub-dirs-list-item" id="${subDir.id}">
			
			<button class="ls" id="${subDir.id}">ls</button>
			<button class='mkdir' id="${subDir.id}">mkdir</button>
			<button class='addFile' id="${subDir.id}">add File</button>
			<g:form resource="${subDir}" method="DELETE">
		        <button class="delete" type="submit" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >delete</button>
		    </g:form> 
	        <div></div>
		</li>
	</div>
</ul>