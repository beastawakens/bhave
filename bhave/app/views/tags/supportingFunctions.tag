<script>

	function dataURItoBlob(dataURI) {
	    // convert base64 to raw binary data held in a string
	    var byteString;
		if (dataURI.split(',')[0].indexOf('base64') >= 0) {
		    byteString = atob(dataURI.split(',')[1]);
		} else {
		    byteString = unescape(dataURI.split(',')[1]);
		}
	
	    // separate out the mime component
	    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0]
	
	    // write the bytes of the string to an ArrayBuffer
	    var ab = new ArrayBuffer(byteString.length);
	    var ia = new Uint8Array(ab);
	    for (var i = 0; i < byteString.length; i++) {
	        ia[i] = byteString.charCodeAt(i);
	    }
	
	    // write the ArrayBuffer to a blob, and you're done
	    var bb;
	    try {
	        bb = new BlobBuilder();
	    } catch(e) {
	        try {
	            bb = new WebKitBlobBuilder();
	        } catch(e) {
	            bb = new MozBlobBuilder();
	        }
	    }
	    bb.append(ab);
	    return bb.getBlob(mimeString);
	}

	function Screenshot(png) {
		this.id;
		this.source = 'data:image/png;base64,' + png;
	}

	TestState = {
		FAIL: -1,
		PENDING: 0,
		SUCCESS: 1
	}

	webdriver.promise.Application.getInstance().on('uncaughtException', function(e) {
		bhaviour.lastSuccess(TestState.FAIL);
		bhaviour.failureMessage('Oh dear: ' + e);
		bhaviour.running(false);
    });

	webdriver.promise.Application.getInstance().addListener('idle', function() { 
		if (bhaviour.lastSuccess() == TestState.PENDING) {
//			console.log(webdriver.promise.Application.getInstance().getHistory());
			bhaviour.running(false);
			bhaviour.lastSuccess(TestState.SUCCESS);
		}
	}, false);

	webdriver.promise.Application.getInstance().addListener('scheduleTask', function() { 
//		console.log(webdriver.promise.Application.getInstance().getSchedule());
		bhaviour.lastSuccess(TestState.PENDING);
		bhaviour.running(true);
	}, false);
	
	
</script>
