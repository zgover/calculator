// Zachary Gover
// JAV1 - 1608
// MainActivity

package com.gover.zachary.calculator;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * MARK: Global Properties
     */

    public String operand = null;
    public TextView display = null;
    public String currentNum = null;
    public Double currentValue = 0.0;
    public Boolean processed = false;

    /**
     * MARK: Default Methods
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView) findViewById(R.id.textView);
        display.setText("0");
    }

    /**
     * MARK: Event Listeners
     */

    public void Num1BtnClick(View view)         { processInput("1"); }

    public void Num2BtnClick(View view)         { processInput("2"); }

    public void Num3BtnClick(View view)         { processInput("3"); }

    public void DivideBtnClick(View view)       { processInput("/"); }

    public void Num4BtnClick(View view)         { processInput("4"); }

    public void Num5BtnClick(View view)         { processInput("5"); }

    public void Num6BtnClick(View view)         { processInput("6"); }

    public void AdditionBtnClick(View view)     { processInput("+"); }

    public void Num7BtnClick(View view)         { processInput("7"); }

    public void Num8BtnClick(View view)         { processInput("8"); }

    public void Num9BtnClick(View view)         { processInput("9"); }

    public void SubtractionBtnClick(View view)  { processInput("-"); }

    public void ClearBtnClick(View view)        { processInput("c"); }

    public void Num0BtnClick(View view)         { processInput("0"); }

    public void EqualsBtnClick(View view)       { processInput("="); }

    public void MultiplyBtnClick(View view)     { processInput("*"); }

    /**
     * MARK: Custom Methods
     */

    public void processInput(String input) {
        String currentText = this.display.getText().toString();
        String lastInput = currentText.substring(currentText.length() - 1);

        // Reset the calculator
        if (input == "c") {
            this.display.setText("0");
            this.currentValue = 0.0;
            this.currentNum = null;
            this.operand = null;
        }
        // Process equation
        else if (input == "=" && this.currentNum != null && this.currentValue != null) {
            this.currentValue = processEquation();

            // If decimal = 0 remove it
            if (this.currentValue % 1 == 0) {
                int val = this.currentValue.intValue();
                this.display.setText(Integer.toString(val));
            } else {
                this.display.setText(currentValue.toString());
            }

            this.currentNum = null;
            this.operand = null;
            this.processed = true;
        }
        // If the calculator has no history set text for the numbered input
        else if (currentText == "0" && this.operand == null && isInt(input)) {
            display.setText(input);
            this.currentValue = Double.parseDouble(input);
        }
        // If last input is a space add the new number
        else if ((lastInput == " " || !isInt(lastInput)) && isInt(input)) {
            display.setText(currentText + input);
            this.currentNum = input;
        }
        // Before we have a history
        else if (operand == null && isInt(lastInput) && isInt(input) && !processed) {
            if (currentText.length() < 1) {
                display.setText(input);
                this.currentValue = Double.parseDouble(input);
            } else {
                String val = this.currentValue.intValue() + "" + input;
                this.currentValue = Double.parseDouble(val);
                display.setText(val);
            }
        }
        // If the last input was a number add the new number
        else if (isInt(lastInput) && isInt(input) && !processed) {
            display.setText(currentText + input);
            this.currentNum = this.currentNum + "" + input;
        }
        // If input is an equation button and operand is null
        // process the equation and update the text
        else if (isInt(lastInput) && !isInt(input) && this.operand == null && input != "=") {
            display.setText(currentText + " " + input + " ");
            this.operand = input;
        }
        // If input is an equation button just add it
        else if (isInt(lastInput) && !isInt(input) && input != "=") {
            this.currentValue = processEquation();
            display.setText(currentText + " " + input + " ");
            this.operand = input;
        }
        // if processed reset on int
        else if (isInt(input) && processed) {
            this.processed = false;
            this.currentNum = null;
            this.operand = null;
            this.currentValue = Double.parseDouble(input);
            this.display.setText(input);
        }
    }

    public double processEquation() {
        Double value = this.currentValue;

        // Determine operand and process equation
        switch (this.operand) {
            case "+":
                value = value + Double.parseDouble(currentNum);
                break;
            case "-":
                value = value - Double.parseDouble(currentNum);
                break;
            case "*":
                value = value * Double.parseDouble(currentNum);
                break;
            case "/":
                value = value / Double.parseDouble(currentNum);
                break;
            default:
                break;
        }

        return value;
    }

    public boolean isInt(String input) {
        Boolean isInt = false;

        // Determine if it is an Int or Operand
        switch (input) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                isInt = true;
                break;
            default:
                    break;
        }

        return isInt;
    }


}
