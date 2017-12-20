import require from 'js/require.js';
function load(jsonArray){
var fs = require('fs');
        fs.readFile('notice.json', 'utf8', function readFileCallback(err, data){
    if (err){
        console.log(err);
    } else {
    obj = JSON.parse(data); //now it an object
    obj.push(jsonArray); //add some data
    json = JSON.stringify(obj); //convert it back to json
    fs.writeFile('notice.json', json, 'utf8', callback); // write it back 
}});
}
        