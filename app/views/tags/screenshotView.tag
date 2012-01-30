<div class="glow" data-bind="visible: screenshots().length > 0">
	<nav>
		<ul class="smallScreenshots" data-bind="foreach: screenshots">
			<li data-bind="attr: {id: 'screenshotSmall_'+$data}">
				<div>
					<a data-bind="attr: {href: '#'+$data}">
						<img class="screenshotThumbnail" data-bind="attr: {src: '/@bhave/screenshot/'+$parent.id()+'/'+$data}"/>
					</a>
					<br/>
					<button data-bind="click: $parent.deleteScreenshot, text: 'Delete'"></button>
				</div>
			</li>
		</ul>
	</nav>
	
	<ul class="largeScreenshots" data-bind="foreach: screenshots">
		<li data-bind="attr: {id: $data}">
			<a href="#home">
				<img class="screenshotLarge" data-bind="attr: {src: '/@bhave/screenshot/'+$parent.id()+'/'+$data}"/>
			</a>		
		</li>
	</ul>
</div>