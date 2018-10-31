package com.nagel.loanapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class PlanActivity extends AppCompatActivity {

    private List<String> items = new ArrayList();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        items.add(getResources().getString(R.string.per));
        items.add(getResources().getString(R.string.intr));
        items.add(getResources().getString(R.string.rep));
        items.add(getResources().getString(R.string.outs));
        for (int n = 1; n <= Loan.getInstance().getPeriods(); ++n) {
            items.add("" + n);
            items.add(String.format("%1.2f", Loan.getInstance().interest(n)));
            items.add(String.format("%1.2f", Loan.getInstance().repayment(n)));
            items.add(String.format("%1.2f", Math.abs(Loan.getInstance().outstanding(n))));
        }
        GridView grid = findViewById(R.id.grid);
        grid.setAdapter(adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
