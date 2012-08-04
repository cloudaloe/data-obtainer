//
// New module for UI leaden management - not stable and should not be run other than for development
// Launches on the command prompt through entering: node invoker.js
//

var http = require('http');
var fs = require('fs');
var agentRunning=false;

//try later to incorporate this library
//var nodetime = require('nodetime');
//nodetime.profile();

console.log('Uber Agent is up.\nUI listening on http://127.0.0.1:1337/.\n');

http.createServer(function (request, response) {
	console.log('Received request - method: ' + request.method + ' url: ' + request.url);
	if (request.method == 'GET') 
		switch(request.url)
		{
			case '/': 
				// serves the html page to the browser
				// need to cache the file more intelligently for scalability
				// nice to have: log the specific error on the server-side
				fs.readFile('./main.html', function(error, content) {
					if (error) {
						response.writeHead(500);
						response.end("Error: could not load main page");
						// this assumes the browser does not need to obtain any css or js referenced in the html.
						// for now that is the case. Otherwise see basic snippets at 
						// http://thecodinghumanist.com/blog/archives/2011/5/6/serving-static-files-from-node-js
					}
					else {
						response.writeHead(200, { 'Content-Type': 'text/html' });
						response.end(content, 'utf-8');
					}
				});
				break;
			case '/run':
				response.writeHead(200, {'Content-Type': 'text/plain'});
				response.write('Running the agent....');				
				invoke(response);				
				break;
			default:
				response.writeHead(200, {'Content-Type': 'text/plain'});	
				response.end('Oops, the requested page was not found....');	
		}	
}).listen(1337, '127.0.0.1');

// problem - this doesn't stream the agent log on screen, only shows it once the agent finishes off
// should use Angular / Meteor / Ajax to stream it
// later also streamm it into a usable place on screen
// later also address the horrible dull formatting
function invoke(response){

	agentRunning=true;
	var time = process.hrtime();
	var spawn = require('child_process').spawn
	child=spawn('java',['-jar', 'target/data-obtainer.jar'])	
	
	//child.stdout.on('data', function (data) { response.write(String(data)); });
	child.stdout.on('data', function (data) { response.write(String(data)); });	
	child.stderr.on('data', function (data) { process.stdout.write('(stderr:) ' + String(data)); });
	child.on('exit', function (code) { 
		invocationDuration = process.hrtime(time);	
		console.log('Child agent finished with code ' + code + "," + " having operated for %d seconds and %d millieseoncds.", invocationDuration[0], invocationDuration[1]/1000000); 
		agentRunning=false;
		response.end();		
	});
}