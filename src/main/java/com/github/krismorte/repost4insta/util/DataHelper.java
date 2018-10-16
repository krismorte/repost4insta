/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.repost4insta.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 *
 * @author c007329
 */
public class DataHelper {

    public static LocalDate dateToLocalDate(Date data) {
        if (data != null) {
            return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else {
            return null;
        }
    }

    public static Date localDateToDate(LocalDate data) {
        return Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate primeiroDiaDoMes(LocalDate dataAtual) {
        return dataAtual.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate ultimoDiaDoMes(LocalDate dataAtual) {
        return dataAtual.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate getDataVencimentoDentroDoMes(LocalDate dataAtual, int diaPagamento) {
        LocalDate dataDespesa = null;
        LocalDate dataCondeferencia = dataAtual.with(TemporalAdjusters.lastDayOfMonth());
        if (diaPagamento <= dataCondeferencia.getDayOfMonth()) {
            dataDespesa = LocalDate.of(dataAtual.getYear(), dataAtual.getMonthValue(), diaPagamento);
        } else {
            dataDespesa = LocalDate.of(dataAtual.getYear(), dataAtual.getMonthValue(), dataCondeferencia.getDayOfMonth());
        }
        return dataDespesa;
    }
}
