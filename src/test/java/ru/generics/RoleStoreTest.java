package ru.generics;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    private RoleStore store;

    @BeforeEach
    void setUp() {
        store = new RoleStore();
        store.add(new Role("1", "Admin"));
    }

    @Test
    void whenAddAndFindThenRoleNameIsAdmin() {
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Admin");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        Role result = store.findById("5");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsAdmin() {
        store.add(new Role("1", "only reading"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceThenRoleNameIsOnlyReading() {
        store.replace("1", new Role("1", "only reading"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("only reading");
    }

    @Test
    void whenNoReplaceUserThenNoChangeRoleName() {
        store.replace("10", new Role("1", "only reading"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Admin");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsAdmin() {
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceOkThenTrue() {
        boolean result = store.replace("1", new Role("1", "only reading"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        boolean result = store.replace("10", new Role("10", "only reading"));
        assertThat(result).isFalse();
    }
}