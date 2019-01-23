//Variablen voor het uitlezen van de ACS71
const int CurrentIn = A0;
int mVperAmp = 100; // use 100 for 20A Module and 66 for 30A Module
int RawValue = 0;
int ACSoffset = 2500;
double Voltage = 0;
double Amps = 0;

//Variablen voor de Moving Average filter
const int numReadings = 5;
int readings[numReadings];      // the readings from the analog input
int readIndex = 0;              // the index of the current reading
int total = 0;                  // the running total
int average = 0;                // the average

bool IsCharching = false;

//spanning meten over 10 ohm weerstand
const int weerstand = 10;
const int weerstandSpanningPoort = A1;
int weerstandSpanningValue = 0;
float WeerstandSpanning = 0;

//Variablen voor de Moving Average filter spanning
const int VolnumReadings = 5;
int Volreadings[numReadings];      // the readings from the analog input
int VolreadIndex = 0;              // the index of the current reading
float Voltotal = 0;                  // the running total
float Volaverage = 0;                // the average

float Current = 0;


//Bypassen van current
const int bypasspin = 2;
bool bypass = false;


void setup()
{
  //Seriele connectie
  Serial.begin(9600);

  //reading 0 maken
  for (int thisReading = 0; thisReading < numReadings; thisReading++) {
    readings[thisReading] = 0;
  }
 for (int thisReading = 0; thisReading < VolnumReadings; thisReading++) {
    Volreadings[thisReading] = 0;
  }
  pinMode(bypasspin, OUTPUT);
}

void loop()
{
  CurrentrichtingMeasurement();
  AverageFilterCurrentRichting();
  currentMeasurement();
  AverageFilterCurrent();

  Current = Volaverage/weerstand;
  Serial.print(Current, 4);
  if(!IsCharching)
  {
    if(Current < 0,1)
    {
      Serial.write("bypass true");
      bypass = true;
    }
    else if(Current > 0,4){
      Serial.write("bypass false");
      bypass = false;
    }   
  }

  if(bypass)
  {
    digitalWrite(bypasspin, true); 
  }
  else {
    digitalWrite(bypasspin,false);
  }
  delay(1000);
}



void currentMeasurement()
{
  weerstandSpanningValue = analogRead(weerstandSpanningPoort);
  WeerstandSpanning = weerstandSpanningValue * (5.0 / 1023.0);//correctie
  Serial.println(WeerstandSpanning);




}

void AverageFilterCurrent()
{
  Voltotal = Voltotal - Volreadings[VolreadIndex];
  // read from the sensor:
  Volreadings[VolreadIndex] = WeerstandSpanning;
  // add the reading to the total:
  Voltotal = Voltotal + Volreadings[VolreadIndex];
  // advance to the next position in the array:
  VolreadIndex = VolreadIndex + 1;

  // if we're at the end of the array...
  if (VolreadIndex >= VolnumReadings) {
    // ...wrap around to the beginning:
    VolreadIndex = 0;

  }

  // calculate the average:
  Volaverage = Voltotal / VolnumReadings;

  Serial.println("spanningOverWeerstand is");

  Serial.println(Volaverage);

}









void CurrentrichtingMeasurement()
{
  RawValue = analogRead(CurrentIn);
  Voltage = (RawValue / 1024.0) * 5000; // Gets you mV
  Amps = ((Voltage - ACSoffset) / mVperAmp) * 100;



  Serial.print("Raw Value = " ); // shows pre-scaled value
  Serial.print(RawValue);
  Serial.print("\t mV = "); // shows the voltage measured
  Serial.print(Voltage, 3); // the '3' after voltage allows you to display 3 digits after decimal point
  Serial.print("\t Amps = "); // shows the voltage measured
  Serial.println(Amps, 3); // the '3' after voltage allows you to display 3 digits after decimal point
}









void AverageFilterCurrentRichting()
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

  if (average > 0)
  {
    IsCharching = true;
  }
  else
  {
    IsCharching = false;

  }
  Serial.println(average);
  Serial.println(IsCharching);

}

