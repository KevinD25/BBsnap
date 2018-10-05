#include <IRremote.h>
#include <IRremoteInt.h>
IRsend irsend;


int lastpress1 = 0;
int lastpress2 = 0;
int debounce = 15;
boolean isEnabled = false;
boolean takeShot = false;




void setup()
{
  //adc_disable();          // adc disable for low power consumption

  DDRB =  0b00001000;    // pin 5 as output for the infrared
  GIMSK = 0b00100000;    // turns on pin change interrupts
  PCMSK = 0b00000110;    // turn on interrupts on pins 6 and 7
  sei();
}
void loop() {
  if (isEnabled)
  {
    irsend.sendNEC(0xFF30CF, 32); // button1 code
    isEnabled = false;
  }

  if (takeShot)
  {
    irsend.sendNEC(0xFF18E7, 23);  // button2 code
    takeShot = false;
  }
}
//interrupt on enable button
ISR(PCINT1_vect)
{
  if (lastpress1 < millis())
  {
    lastpress1 = millis() + debounce;

                 if (isEnabled = false)
                 {
      isEnabled = true;
    }

    else
    {
      isEnabled = false;
    }

  }
}


//interrupt on shot button
ISR(PCINT2_vect)
{
  if (lastpress2 < millis())
  {
    lastpress2 = millis() + debounce;

                 if (takeShot = false)
    {
      takeShot = true;
    }

    else
    {
      takeShot = true;
    }

  }
}

