int btn_enable, btn_shot = 2,3;
int time = 300;
int lastpress1, lastpress2 = 0;
int debouncetime = 15;



void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(btn_enable, INPUT)
  pinmode(btn_shot, INPUT);
  attachInterrupt(digitalPinToInterrupt(btn_enable),inter_enable, RISING);
  attachInterrupt(digitalPinToInterrupt(btn_shot),inter_shot, RISING);

}

void loop() {
  // put your main code here, to run repeatedly:

}
void inter_enable()
{
  lastpress2 = millis();
  debouncetime+= 50;
  
}

void inter_shot()
{
  lastpress2 = millis();
  debouncetime+= 50;
}
