/*
 * This file is generated by jOOQ.
 */
package org.autotesting.database.tables;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.autotesting.database.Keys;
import org.autotesting.database.Public;
import org.autotesting.database.tables.Currency.CurrencyPath;
import org.autotesting.database.tables.records.CandleRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Candle extends TableImpl<CandleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.candle</code>
     */
    public static final Candle CANDLE = new Candle();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CandleRecord> getRecordType() {
        return CandleRecord.class;
    }

    /**
     * The column <code>public.candle.candle_id</code>.
     */
    public final TableField<CandleRecord, Integer> CANDLE_ID = createField(DSL.name("candle_id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.candle.open_price</code>.
     */
    public final TableField<CandleRecord, Double> OPEN_PRICE = createField(DSL.name("open_price"), SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>public.candle.close_price</code>.
     */
    public final TableField<CandleRecord, Double> CLOSE_PRICE = createField(DSL.name("close_price"), SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>public.candle.high_price</code>.
     */
    public final TableField<CandleRecord, Double> HIGH_PRICE = createField(DSL.name("high_price"), SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>public.candle.low_price</code>.
     */
    public final TableField<CandleRecord, Double> LOW_PRICE = createField(DSL.name("low_price"), SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>public.candle.currency_id</code>.
     */
    public final TableField<CandleRecord, Integer> CURRENCY_ID = createField(DSL.name("currency_id"), SQLDataType.INTEGER, this, "");

    private Candle(Name alias, Table<CandleRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Candle(Name alias, Table<CandleRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.candle</code> table reference
     */
    public Candle(String alias) {
        this(DSL.name(alias), CANDLE);
    }

    /**
     * Create an aliased <code>public.candle</code> table reference
     */
    public Candle(Name alias) {
        this(alias, CANDLE);
    }

    /**
     * Create a <code>public.candle</code> table reference
     */
    public Candle() {
        this(DSL.name("candle"), null);
    }

    public <O extends Record> Candle(Table<O> path, ForeignKey<O, CandleRecord> childPath, InverseForeignKey<O, CandleRecord> parentPath) {
        super(path, childPath, parentPath, CANDLE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class CandlePath extends Candle implements Path<CandleRecord> {
        public <O extends Record> CandlePath(Table<O> path, ForeignKey<O, CandleRecord> childPath, InverseForeignKey<O, CandleRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private CandlePath(Name alias, Table<CandleRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public CandlePath as(String alias) {
            return new CandlePath(DSL.name(alias), this);
        }

        @Override
        public CandlePath as(Name alias) {
            return new CandlePath(alias, this);
        }

        @Override
        public CandlePath as(Table<?> alias) {
            return new CandlePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<CandleRecord, Integer> getIdentity() {
        return (Identity<CandleRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<CandleRecord> getPrimaryKey() {
        return Keys.CANDLE_PKEY;
    }

    @Override
    public List<ForeignKey<CandleRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CANDLE__CANDLE_CURRENCY_ID_FKEY);
    }

    private transient CurrencyPath _currency;

    /**
     * Get the implicit join path to the <code>public.currency</code> table.
     */
    public CurrencyPath currency() {
        if (_currency == null)
            _currency = new CurrencyPath(this, Keys.CANDLE__CANDLE_CURRENCY_ID_FKEY, null);

        return _currency;
    }

    @Override
    public Candle as(String alias) {
        return new Candle(DSL.name(alias), this);
    }

    @Override
    public Candle as(Name alias) {
        return new Candle(alias, this);
    }

    @Override
    public Candle as(Table<?> alias) {
        return new Candle(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Candle rename(String name) {
        return new Candle(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Candle rename(Name name) {
        return new Candle(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Candle rename(Table<?> name) {
        return new Candle(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Candle where(Condition condition) {
        return new Candle(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Candle where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Candle where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Candle where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Candle where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Candle where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Candle where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Candle where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Candle whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Candle whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
