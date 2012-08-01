//
// New module for UI leaden management - not stable and should not be run other than for development
// Launch on command prompt: node invoker.js
//

var http = require('http');
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
			case '/run':
				response.writeHead(200, {'Content-Type': 'text/plain'});
				response.write('Running the agent....');				
				invoke(response);				
				break;
			default:
				response.writeHead(200, {'Content-Type': 'text/plain'});	
				response.end('Welcome to the Uber Agent....');	
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