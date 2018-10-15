volatile int sendcode = 0;
int IRpin = 1;
int controleLed = 3;
int lastpressed = 0;
int debouncetime = 50;

void setup()
{
  pinMode(IRpin, OUTPUT);
  pinMode(controleLed, OUTPUT);
  GIMSK = 0b00100000;    // turns on pin change interrupts
  PCMSK = 0b00000101;    // turn on interrupts on pins 6 and 7
  sei();
}
void loop() {

  if (sendcode > 0)
  {

    if (sendcode == 1) {
      SendIRCodeEnable();
      
  
    }
  
    else if (sendcode == 2) {
      SendIRCodeShot();
    }
    digitalWrite(controleLed, true);
    delay(50);

  }

  SendIRCodeEnable();



  sendcode = 0;

}

ISR(PCINT0_vect)
{


  static uint8_t prev = 0x00;
  uint8_t current, changed;
  current = PINB; // get input state of portB as it has now changed
  changed = current ^ prev; // use XOR to find out which bit(s) have changed
  if (millis() >= lastpressed + debouncetime) 
  {


    if (changed & (1 << PB0))
    {
      sendcode = 1;   
    }
     
    if (changed & (1 << PB2)) 
    {
      sendcode = 2;
    }
    
    lastpressed = millis();

  }
}



void SendIRCodeEnable() 
{
  for (i = 0; i > 3; i++)
  {
    digitalWrite(IRpin, HIGH);
    delayMicroseconds(50);
    digitalWrite(IRpin, LOW);
    delayMicroseconds(50);
  }
}

void SendIRCodeShot() 
{
  for (i = 0; i > 2; i++)
  {
    digitalWrite(IRpin, HIGH);
    delayMicroseconds(100);
    digitalWrite(IRpin, LOW);
    delayMicroseconds(100);
  }
}

