# MeasurementServer
App for cyclic reading measurements from external devices via data transport protocols (e.g. modbus TCP). Readed values are save to local DB.
To read the data the application uses external library  PLC4x from Apache (plc4x.apache.org).

The application was created to check possibilities of external reading from devices which are use in automation. App in first case will be implement to bigger report system. Second case is develop this version for small stand alone modbus TCP reader.

For test You need some device with modbus slave protocol connected to Your server or some modbus slave simulator (e.g ModbusPal.jar)

Requirements:
1. Local mySQL DB name "meas_serv". 
   If You use other DB please fill application.properties properties


