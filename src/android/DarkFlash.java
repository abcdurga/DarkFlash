package cordova.plugin.darkflash;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class DarkFlash extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }else if(action.equals("openCamera")){
            this.openCamera(args, callbackContext);
            return true;
        }else{
            return false;
        }
        
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void openCamera(JSONArray args, CallbackContext callbackContext) {

        // open camera and check camera luminosity is drk or light
       
        if (args != null) {
            callbackContext.success("Inside NATIVE JAVA successfully");
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }


        
    }

    
}
