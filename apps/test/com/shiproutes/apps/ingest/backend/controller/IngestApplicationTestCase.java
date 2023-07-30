package com.shiproutes.apps.ingest.backend.controller;

import com.shiproutes.apps.ApplicationTestCase;
import org.springframework.transaction.annotation.Transactional;

@Transactional("ingest-transaction_manager")
public abstract class IngestApplicationTestCase extends ApplicationTestCase {
}
