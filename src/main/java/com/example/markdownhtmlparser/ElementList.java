package com.example.markdownhtmlparser;

import java.util.ArrayList;

public class ElementList {
    ArrayList<Element> elements;
    InputType type;
    public ElementList(String input, InputType type) {
        this.type = type;
        switch (type) {
            case HTML -> fromHTML(input);
            case MARKDOWN -> fromMarkdown(input);
        }

    }

    private void fromHTML(String input) {
        /*
        W przypadku odczytywania HTML, musimy zbudować drzewo node'ów,
        które będzie reprezentowało nasz dokument.
         */
    }

    private void fromMarkdown(String input) {
        /*
        Kiedy odczytujemy Markdowna, to na podstawie pierwszego znaku określamy jakiego typu jest to element.
        Jeśli znak w następnej linii się powtarza to zapewne jest to kontynuacja elementu
        Trzeba też pamiętać o pogrubianiu, podkreślaniu itp. czyli znaczniki w środku.
         */
    }
}
