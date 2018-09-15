package com.artyom_panfilenko.gwentdeckbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.artyom_panfilenko.gwentdeckbuilder.activities.NewDeckActivity;
import com.artyom_panfilenko.gwentdeckbuilder.adapters.DeckRVAdapter;
import com.artyom_panfilenko.gwentdeckbuilder.database.DeckHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*Тема проекта - программа для сборки колод для карточной игры по вселенной Ведьмака "Гвинт"
    Я выбрал эту тему т.к. действительно полезных идей,которые я мог бы решить лучще,чем существующие аналоги я не нашёл,
    я решил сделать то,что интересно мне.
    В плэймаркете есть только один конкурент "Roach",но он не очень удобный т.к. меню добавления карт в колоду и колода отдельно,
    и приходится гадать,какие карты уже положил,а какие нет(ну или каждый раз переключаться),и его оформление не соответсвует стилистике Гвинта

    Описание классов:
    В MainActivity у меня находится список колод (класс Deck,файл разметки deck_rv),реализованный при помощи RecyclerView (класс DeckRVAdapter)
    При нажатии на FAB открывается меню создания новой колоды(NewDeckActivity,файл разметки new_deck),а при нажатии на колоду,активность с составом колоды
    и возможностью её редактирования(туда же перенаправляет меню создания новой колоды после указания названия,фракции и лидера колоды).

    В DeckMenu можно изменить название и лидера колоды,а ниже находится поиск среди карт(по списку из всех карт(в нём хранятся объекты Card,реализован при помощи CardsRVAdapter)
    ,который находится слева).Справа находится список карт,которые уже есть в колоде,он отличается от преыдущего(у карт указано название и их количество в колоде,цветом их редкости)
    Реализован при помощи DeckCardsRVAdapter,в котором находятся объекты DeckCard (наследник Card с добавлением параметра количества карт в колоде)

    Карты и колоды хранятся в базе данных DBHelper,для каждой из двух таблиц реализован класс для добавления удаления и редактирования CardsHelper и DeckHelper

    Список карт в колодах будет храниться в строковых файлах,где через пробел будут перечислены ID карт,которые будут извелкаться методом split(похожим образом хранятся картинки -
    в базе данных хранятся пути к ним в файле програмы)

    Что осталось сделать,но я ещё не пробовал сам:
    1)Сохранение и считывание колод из базы данных(саму базу данных уже проверял,поэтому трудностей возникнуть не должно)
    2)Реализовать поиск(будет искать по введенному в строку слову,искать будет по всему:и по названию и по фракции и т.д.,не очень дружелюбно к пользователю,
    зато просто в реализации (уже придумал,как оптимизировать,так что по каждому свойству каждой карты проезжаться не будет) )
    3)Сохранение и считывание колод (тоже всё продумал,осталось реализовать)
    Наполнение базы данных карт(на данный момент она абсолютно пуста,заполню лишь демонстрационными данными в небольшом количестве т.к. в октябре в игре будет крупное дополнение,
    в котором обещают изменить 70% карт)

    Что у меня не получилось,и нужна помощь:
    1)Я не смог в DeckCardsRVAdapter'е найти способ изменить цвет текста элемента
    2)Не получилось реализовать изменение содержимого 2 spinnera,при выборе пунктов в 1 в NewDeckActivity
    3)(главная проблема,если не возникнет новых трудностей)Я примерно знаю,чего я хочу от дизайна,но сам он не получается вообще никак
    (минималистичный (без использования всяких картинок),но довольно мрачный(тёмно-серый фон,может ещё что-нибудь)
    основная проблема тут в аккуратном и красивом расположении различных деталей относительно друг-друга

    */
    RecyclerView rv;
    List<Deck> decks;

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
                Intent intent = new Intent(MainActivity.this, NewDeckActivity.class);
                startActivity(intent);
            }
        });


        DeckHelper deckHelper = new DeckHelper(this);
        decks = deckHelper.getAllTest(10);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(new DeckRVAdapter(decks, this));
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
