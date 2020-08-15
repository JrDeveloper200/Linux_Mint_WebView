package com.isacdeveloper.linuxmintwebview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicia os componentes do Aplicativo
        initWebview();

        //Cria o Webview e recebe métodos internos que sobreescrevem algumas ações, como o inicio do carregar da pagina e a finalização da mesma
        webView.setWebViewClient(new WebViewClient() {

            //FLUXO DO SITE PRINCIPAL
            private static final String URL_PRINCIPAL = "projectjrdeveloper.000webhostapp.com";
            private static final String URL_GITHUB = "jrdeveloper200.github.io";
            private static final String URL_LINUX_MINT = "linuxmint-installation-guide.readthedocs.io";

            //SOCIAIS INTERNAS NA WEBVIEW
            private static final String URL_FACEBOOK = "www.facebook.com";
            private static final String URL_FACEBOOK_M = "m.facebook.com";
            private static final String URL_TWITTER = "twitter.com";
            private static final String URL_TWITTER_M = "mobile.twitter.com";
            private static final String URL_BLOG_LINUX = "blog.linuxmint.com";


            /**
             * Método que exibe o ProgressBar
             *
             * @param view
             * @param url
             * @param favicon
             */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            /**
             * Método que retira o ProgressBar
             * @param view
             * @param url
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);
            }

            /**
             * Métodos usado para carregar URLs de dominio definidos na própria WebView
             * @param view
             * @param request
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request.getUrl().getHost().equals(URL_PRINCIPAL) || (request.getUrl().getHost().equals(URL_GITHUB) || (request.getUrl().getHost().equals(URL_LINUX_MINT) || (request.getUrl().getHost().equals(URL_BLOG_LINUX)) || (request.getUrl().getHost().equals(URL_FACEBOOK)) || (request.getUrl().getHost().equals(URL_FACEBOOK_M)) || (request.getUrl().getHost().equals(URL_TWITTER)) || (request.getUrl().getHost().equals(URL_TWITTER_M))
                ))) {
                    return false;
                }

                //SE CONDIÇÕES DE DOMINIO FOREM FALSAS EXIBE UMA NOVA ACTIVITY. UTIL PARA OS DOWNLOADS DA PÁGINA
                Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
                startActivity(intent);
                return true;

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Uri.parse(url).getHost().equals(URL_PRINCIPAL) || (Uri.parse(url).getHost().equals(URL_GITHUB) || (Uri.parse(url).getHost().equals(URL_LINUX_MINT) || (Uri.parse(url).getHost().equals(URL_BLOG_LINUX)) || (Uri.parse(url).getHost().equals(URL_FACEBOOK)) || (Uri.parse(url).getHost().equals(URL_FACEBOOK_M)) || (Uri.parse(url).getHost().equals(URL_TWITTER)) || (Uri.parse(url).getHost().equals(URL_TWITTER_M))
                ))) {
                    return false;
                }

                //SE CONDIÇÕES DE DOMINIO FOREM FALSAS EXIBE UMA NOVA ACTIVITY. UTIL PARA OS DOWNLOADS DA PÁGINA
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
        });

    }

    /**
     * Método que escuta as ações de touch do usuario
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Inicia o Webview
     */
    private void initWebview() {

        webView = findViewById(R.id.webView);
        webView.loadUrl("https://projectjrdeveloper.000webhostapp.com/");
        webView.getSettings().setJavaScriptEnabled(true);

        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);
    }

}