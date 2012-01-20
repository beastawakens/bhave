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

	function Bhaviour(language, command) {
		this.language = ko.observable(language);
		this.command = ko.observable(command);
	}
	
	function Screenshot(png) {
		this.id;
		this.source = 'data:image/png;base64,' + png;
		
	}
	
</script>
