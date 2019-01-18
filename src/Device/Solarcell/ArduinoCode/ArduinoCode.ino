//Variablen voor het uitlezen van de ACS71
const int CurrentIn = A0;
int mVperAmp = 100; // use 100 for 20A Module and 66 for 30A Module
int RawValue= 0;
int ACSoffset = 2500; 
double Voltage = 0;
double Amps = 0;

//Variablen voor de Moving Average filter
const int numReadings = 5;
int readings[numReadings];      // the readings from the analog input
int readIndex = 0;              // the index of the current reading
int total = 0;                  // the running total
int average = 0;                // the average

//Variable voor uitlezen van spanning batterijen
const int BatteryIn = A1;
int sensorValue=0;
float batvoltage =0;

//SollarCelByPas
const int bypasspin = 2;
double nominalVoltage = 3.7;
bool bypassing = false;

const int output = A2;
int outputvalue=0;
float outputvoltage =0;
void setup() 
{
  //Seriele connectie 
  Serial.begin(9600);

  //reading 0 maken
  for (int thisReading = 0; thisReading < numReadings; thisReading++) {
   readings[thisReading] = 0;
  }
  pinMode(bypasspin,OUTPUT);
}

void loop() 
{
 CurrentMeasurement();
 
  AvarageFilter();
  
  sensorValue = analogRead(BatteryIn);
  batvoltage = sensorValue * (5.0 / 1023.0);//correctie
    Serial.print("bat voltage ");
  Serial.println(batvoltage);

  batvoltage = batvoltage -0.20; 
  Serial.print("bat voltage ");
  Serial.println(batvoltage);

  outputvalue = analogRead(output);
  outputvoltage = outputvalue * (5.0 / 1023.0);
    Serial.print("outputvoltage ");
  Serial.println(outputvoltage);
 
  
  if (batvoltage > 3.7)
  {
    bypassing = true;
  }
  else if (batvoltage < 3.5 && bypassing)
  {
    bypassing = false;
  }

  if (bypassing)
  {
    digitalWrite(bypasspin,true);
  }
  delay(1000);
}



void CurrentMeasurement()
{
 RawValue = analogRead(CurrentIn);
 Voltage = (RawValue / 1024.0) * 5000; // Gets you mV
 Amps = ((Voltage - ACSoffset) / mVperAmp) * 100;

 
 
 Serial.print("Raw Value = " ); // shows pre-scaled value 
 Serial.print(RawValue); 
 Serial.print("\t mV = "); // shows the voltage measured 
 Serial.print(Voltage,3); // the '3' after voltage allows you to display 3 digits after decimal point
 Serial.print("\t Amps = "); // shows the voltage measured 
 Serial.println(Amps,3); // the '3' after voltage allows you to display 3 digits after decimal point
}

void AvarageFilter()
{
  total = total - readings[readIndex];
  // read from the sensor:
  readings[readIndex] = Amps;
  // add the reading to the total:
  total = total + readings[readIndex];
  // advance to the next position in the array:
  readIndex = readIndex + 1;

  // if we're at the end of the array...
  if (readIndex >= numReadings) {
    // ...wrap around to the beginning:
    readIndex = 0;

    }

     // calculate the average:
  average = total / numReadings;
  // send it to the computer as ASCII digits
  Serial.println(average);
}

