package ipg.vagner.adivinha;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;


public class MainActivity extends AppCompatActivity {
    private GeradorNumerosAdivinhar geradorNumeros;
    private int numeroAdivinhar;
    private int tentativas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adivinha();
            }
        });

        geradorNumeros = new GeradorNumerosAdivinhar();
        novoJogo();
    }

    private void novoJogo() {
        numeroAdivinhar = geradorNumeros.getProximoNumeroAdivinhar();
        tentativas = 0;
        Toast.makeText(this, getString(R.string.novo_jogo), Toast.LENGTH_LONG).show();
    }

    private void adivinha() {
        EditText editTextNumero = (EditText) findViewById(R.id.editTextNumero);
        String textoNumero = editTextNumero.getText().toString();

        if (textoNumero.isEmpty()) {
            editTextNumero.setError(getString(R.string.introduza_numero));
            editTextNumero.requestFocus();
            return;
        }

        int numero;

        try {
            numero = Integer.parseInt(textoNumero);
        } catch (NumberFormatException e) {
            editTextNumero.setError(getString(R.string.numero_invalido));
            editTextNumero.requestFocus();
            return;
        }

        if (numero < 1 || numero > 10) {
            editTextNumero.setError(getString(R.string.numero_invalido));
            editTextNumero.requestFocus();
            return;
        }

        verificaAcertou(numero);
    }

    private void verificaAcertou(int numero) {
        tentativas++;

        if (numero == numeroAdivinhar) {
            acertou();
        } else if (numero < numeroAdivinhar) {
            Toast.makeText(this, getString(R.string.numero_maior), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getString(R.string.numero_menor), Toast.LENGTH_LONG).show();
        }
    }

    private void acertou() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(R.string.acertou);
        alertDialogBuilder.setMessage(
                getString(R.string.acertou_ao_fim_de) +
                tentativas +
                getString(R.string.jogar_novamente)
        );

        alertDialogBuilder.setPositiveButton(
                getString(R.string.sim),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        novoJogo();
                    }
                }
        );

        alertDialogBuilder.setNegativeButton(
                getString(R.string.nao),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }
        );

        alertDialogBuilder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, getString(R.string.versao), Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_novo) {
            actionNovo();
            return true;
        } else if (id == R.id.action_estatisticas) {
            actionEstatisticas();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void actionEstatisticas() {
        // todo: mostrar estatísticas
    }

    private void actionNovo() {
        // todo: perguntar se tem a certeza e iniciar novo jogo
    }

}
