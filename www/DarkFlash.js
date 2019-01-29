var exec = require('cordova/exec');

module.exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'DarkFlash', 'coolMethod', [arg0]);
};


module.exports.openCamera = function(arg0,success,error)
{
    exec(success,error,'DarkFlash','openCamera',[arg0]);
}
