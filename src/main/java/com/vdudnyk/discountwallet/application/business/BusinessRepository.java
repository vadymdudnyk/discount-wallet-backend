package com.vdudnyk.discountwallet.application.business;

import com.vdudnyk.discountwallet.application.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface BusinessRepository extends JpaRepository<Business, Long> {
    List<Business> findAllByAdministrator(User administrator);
}
