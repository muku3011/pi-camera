Currently, updated (In Progress)

https://app.circleci.com/pipelines/github/muku3011

java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:9091 -jar rbpi-camera.jar

java -jar rbpi-camera.jar --camera.user=TODO --camera.password=TODO --camera.role=TODO

libcamera-still

libcamera-jpeg

--version [=arg(=1)] (=0)             Displays the build version number
--width arg (=0)                      Set the output image width (0 = use default value)
--height arg (=0)                     Set the output image height (0 = use default value)
-t [ --timeout ] arg (=5000)          Time (in ms) for which program runs
-o [ --output ] arg                   Set the output file name
--immediate [=arg(=1)] (=0)           Perform first capture immediately, with no preview phase

--shutter arg (=0)                    Set a fixed shutter speed in microseconds
--metering arg (=centre)              Set the metering mode (centre, spot, average, custom)
--exposure arg (=normal)              Set the exposure mode (normal, sport)
--awb arg (=auto)                     Set the AWB mode (auto, incandescent, tungsten, fluorescent, indoor, daylight,
cloudy, custom)
--brightness arg (=0)                 Adjust the brightness of the output images, in the range -1.0 to 1.0
--contrast arg (=1)                   Adjust the contrast of the output image, where 1.0 = normal contrast
--saturation arg (=1)                 Adjust the colour saturation of the output, where 1.0 = normal and 0.0 =
greyscale
--sharpness arg (=1)                  Adjust the sharpness of the output image, where 1.0 = normal sharpening

--timelapse arg (=0)                  Time interval (in ms) between timelapse captures
--framestart arg (=0)                 Initial frame counter value for timelapse captures

-q [ --quality ] arg (=93)            Set the JPEG quality parameter
-e [ --encoding ] arg (=jpg)          Set the desired output encoding, either jpg, png, rgb, bmp or yuv420

