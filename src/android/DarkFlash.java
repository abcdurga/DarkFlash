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

        PictureCallback fCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {

         Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
          if ((bm.getWidth() > 200) && (bm.getHeight() > 200))
         { 
           int lum = Color.red(bm.getPixel(50, 50));
           lum += Color.red(bm.getPixel(100, 100));
           lum += Color.red(bm.getPixel(150, 150));
           lum += Color.red(bm.getPixel(200, 200));
           lum += Color.green(bm.getPixel(50, 50));
           lum += Color.green(bm.getPixel(100, 100));
           lum += Color.green(bm.getPixel(150, 150));
           lum += Color.green(bm.getPixel(200, 200));
           lum += Color.blue(bm.getPixel(50, 50));
           lum += Color.blue(bm.getPixel(100, 100));
           lum += Color.blue(bm.getPixel(150, 150));
           lum += Color.blue(bm.getPixel(200, 200));
           lum = lum/12;

         if (lum<3)
             tvMain.setText("dark");
             Camera cam = camera;     
            Parameters p = cam.getParameters();
            p.setFlashMode(Parameters.FLASH_MODE_TORCH);
            cam.setParameters(p);
            cam.startPreview();
        else
             tvMain.setText("light");    

            camready = true;
         }

      }
    };

        var camera = Camera.open(findFrontFacingCamera());
        Camera.Parameters param=camera.getParameters();
        param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        param.set("rawsave-mode", "1");
        param.setPictureFormat(ImageFormat.RGB_565);
        camera.setParameters(param);  
        camera.startPreview();
        camera.takePicture(null, null, fCallback);

        
        if (args != null) {
            callbackContext.success("Inside NATIVE JAVA successfully");
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }


        
    }

    
}
