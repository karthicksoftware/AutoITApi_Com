java -jar selenium-server-standalone-2.53.1.jar -role node -port 8080 -browser "browserName=internet explorer,version=11,maxInstances=5,platform=WINDOWS" -browser "browserName=firefox,version=41.0,maxInstances=5,platform=WINDOWS" -browser "browserName=chrome,version=45.0,maxInstances=5,platform=WINDOWS" -maxSession=3 -Dwebdriver.ie.driver="D:\Workspace\Interview\MyDrivers\IEDriverServer.exe" -Dwebdriver.chrome.driver="D:\Workspace\Interview\MyDrivers\chromedriver.exe" -hub http://192.168.128.219:4444/grid/register