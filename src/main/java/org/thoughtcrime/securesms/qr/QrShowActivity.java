package org.thoughtcrime.securesms.qr;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.b44t.messenger.DcContext;

import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.connect.DcEventCenter;
import org.thoughtcrime.securesms.connect.DcHelper;
import org.thoughtcrime.securesms.util.DynamicTheme;
import org.thoughtcrime.securesms.util.Util;

public class QrShowActivity extends AppCompatActivity {

    private final DynamicTheme dynamicTheme = new DynamicTheme();

    public final static String CHAT_ID = "chat_id";

    DcEventCenter dcEventCenter;

    DcContext dcContext;
    QrShowFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dynamicTheme.onCreate(this);

        setContentView(R.layout.activity_qr_show);
        fragment = (QrShowFragment)getSupportFragmentManager().findFragmentById(R.id.qrScannerFragment);

        dcContext = DcHelper.getContext(this);
        dcEventCenter = DcHelper.getEventCenter(this);

        Bundle extras = getIntent().getExtras();
        int chatId = 0;
        if (extras != null) {
            chatId = extras.getInt(CHAT_ID);
        }

        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        if (chatId != 0) {
            // verified-group
            String groupName = dcContext.getChat(chatId).getName();
            supportActionBar.setTitle(groupName);
            supportActionBar.setSubtitle(R.string.qrshow_join_group_title);
        } else {
            // verify-contact
            String selfName = DcHelper.get(this, DcHelper.CONFIG_DISPLAY_NAME); // we cannot use MrContact.getDisplayName() as this would result in "Me" instead of
            if (selfName.isEmpty()) {
                selfName = DcHelper.get(this, DcHelper.CONFIG_CONFIGURED_ADDRESS, "unknown");
            }
            supportActionBar.setTitle(selfName);
            supportActionBar.setSubtitle(R.string.qrshow_join_contact_title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.qr_show, menu);
      menu.findItem(R.id.new_classic_contact).setVisible(false);
      menu.findItem(R.id.paste).setVisible(false);
      menu.findItem(R.id.load_from_image).setVisible(false);
      Util.redMenuItem(menu, R.id.withdraw);
      return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dynamicTheme.onResume(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

      int itemId = item.getItemId();
      if (itemId == android.R.id.home) {
        finish();
        return true;
      } else if (itemId == R.id.withdraw) {
        fragment.withdrawQr();
      }

        return false;
    }
}
