package component;

import com.flextrade.jfixture.JFixture;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class TestDefaultSupportedSimpleTypes {

    private JFixture fixture;

    @Before
    public void initialise() {
        fixture = new JFixture();
    }

    @Test
    public void strings_are_supported_by_default() {
        assertThat(fixture.create(String.class), is(notNullValue()));
    }

    @Test
    public void numbers_are_supported_by_default() {
        assertThat(fixture.create(Byte.class), is(notNullValue()));
        assertThat(fixture.create(Short.class), is(notNullValue()));
        assertThat(fixture.create(Integer.class), is(notNullValue()));
        assertThat(fixture.create(Long.class), is(notNullValue()));
        assertThat(fixture.create(Float.class), is(notNullValue()));
        assertThat(fixture.create(Double.class), is(notNullValue()));
        assertThat(fixture.create(BigInteger.class), is(notNullValue()));
        assertThat(fixture.create(BigDecimal.class), is(notNullValue()));
    }

    @Test
    public void uuids_are_supported_by_default() {
        assertThat(fixture.create(UUID.class), is(notNullValue()));
    }

    @Test
    public void dates_are_supported_by_default() {
        assertThat(fixture.create(Date.class), is(notNullValue()));
    }

    @Test
    public void calendars_are_supported_by_default() {
        assertThat(fixture.create(Calendar.class), is(notNullValue()));
    }

    @Test
    public void booleans_are_supported_by_default() {
        assertThat(fixture.create(Boolean.class), is(notNullValue()));
    }

    @Test
    public void characters_are_supported_by_default() {
        assertThat(fixture.create(Character.class), is(notNullValue()));
    }

    @Test
    public void enums_are_supported_by_default() {
        assertThat(fixture.create(TestEnum.class), is(notNullValue()));
    }

    @Test
    public void arrays_are_supported_by_default() {
        assertThat(fixture.create(TestEnum.class), is(notNullValue()));
    }

    @Test
    public void urls_are_supported_by_default() {
        assertThat(fixture.create(URL.class), is(notNullValue()));
    }

    @Test
    public void uris_are_supported_by_default() {
        assertThat(fixture.create(URI.class), is(notNullValue()));
    }

    private enum TestEnum { A, B, C }
}
