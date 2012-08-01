//
// New module for UI leaden management 
//


var http = require('http');
//var nodetime = require('nodetime');
//nodetime.profile();

http.createServer(function (req, res) {
  res.writeHead(200, {'Content-Type': 'text/plain'});
  res.end('Running the agent....');
  invoke();
}).listen(1337, '127.0.0.1');

console.log('Uber Agent is up.\nUI listening on http://127.0.0.1:1337/.\n');

function invoke(){
	var spawn = require('child_process').spawn

	child=spawn('java',['-jar', 'target/data-obtainer.jar'])
	child.stdout.on('data', function (data) { process.stdout.write(data); });
	child.stderr.on('data', function (data) { process.stdout.write('(stderr:) ' + String(data)); });

	child.on('exit', function (code) { 
		console.log('child agent process finished with code ' + code); 
	});
}

function console_invoke(){ // to be deprecated
	var spawn = require('child_process').spawn
	console.time('Child agent invocation took');
	child=spawn('java',['-jar', 'target/data-obtainer.jar'])
	child.stdout.on('data', function (data) { process.stdout.write(data); });
	child.stderr.on('data', function (data) { process.stdout.write('(stderr:) ' + String(data)); });
	child.on('exit', function (code) { 
	console.log('child agent process finished with code ' + code); 
	console.timeEnd('Child agent invocation took');
});
}