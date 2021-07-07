package me.realized.tokenmanager.config;

import me.realized.tokenmanager.TokenManagerPlugin;
import me.realized.tokenmanager.util.config.AbstractConfiguration;
import me.realized.tokenmanager.util.config.convert.Converter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class Config extends AbstractConfiguration<TokenManagerPlugin> {

    private int version;
    private boolean checkForUpdates;
    private String onlineMode;
    private boolean altPrevention;
    private int defaultBalance;
    private long sendMin;
    private long sendMax;
    private boolean openSelectedEnabled;
    private String openSelectedShop;
    private String confirmPurchaseTitle;
    private String confirmPurchaseConfirm;
    private String confirmPurchaseCancel;
    private int clickDelay;
    private boolean checkInventoryFull;
    private boolean logPurchases;
    private boolean mysqlEnabled;
    private String mysqlUsername;
    private String mysqlPassword;
    private String mysqlHostname;
    private String mysqlPort;
    private String mysqlDatabase;
    private String mysqlTable;
    private String mysqlUrl;
    private boolean redisEnabled;
    private String redisServer;
    private int redisPort;
    private String redisPassword;
    private boolean registerEconomy;
    private int balanceTopUpdateInterval;

    public Config(final TokenManagerPlugin plugin) {
        super(plugin, "config");
    }

    @Override
    protected void loadValues(FileConfiguration configuration) throws Exception {
        if (!configuration.isInt("config-version")) {
            configuration = convert(new Converter2_3());
        } else if (configuration.getInt("config-version") < getLatestVersion()) {
            configuration = convert(null);
        }

        version = configuration.getInt("config-version");
        checkForUpdates = configuration.getBoolean("check-for-updates", true);
        onlineMode = configuration.getString("online-mode", "auto");
        altPrevention = configuration.getBoolean("alt-prevention", false);
        defaultBalance = configuration.getInt("default-balance", 25);
        sendMin = configuration.getInt("send-amount-limit.min", 1);
        sendMax = configuration.getInt("send-amount-limit.max", -1);
        openSelectedEnabled = configuration.getBoolean("shop.open-selected.enabled", false);
        openSelectedShop = configuration.getString("shop.open-selected.shop", "example").toLowerCase();
        confirmPurchaseTitle = configuration.getString("shop.confirm-purchase-gui.title", "Confirm Your Purchase");
        confirmPurchaseConfirm = configuration.getString("shop.confirm-purchase-gui.confirm-button", "STAINED_CLAY:5 1 name:&a&lBUY lore:&7Price:_&a%price%_tokens");
        confirmPurchaseCancel = configuration.getString("shop.confirm-purchase-gui.cancel-button", "STAINED_CLAY:14 1 name:&c&lCANCEL");
        clickDelay = configuration.getInt("shop.click-delay", 0);
        checkInventoryFull = configuration.getBoolean("shop.check-inventory-full", false);
        logPurchases = configuration.getBoolean("shop.log-purchases", false);
        mysqlEnabled = configuration.getBoolean("data.mysql.enabled", false);
        mysqlUsername = configuration.getString("data.mysql.username", "root");
        mysqlPassword = configuration.getString("data.mysql.password", "password");
        mysqlHostname = configuration.getString("data.mysql.hostname", "127.0.0.1");
        mysqlPort = configuration.getString("data.mysql.port", "3306");
        mysqlDatabase = configuration.getString("data.mysql.database", "database");
        mysqlTable = configuration.getString("data.mysql.table", "tokenmanager");
        mysqlUrl = configuration.getString("data.mysql.url", "jdbc:mysql://%hostname%:%port%/%database%");
        redisEnabled = configuration.getBoolean("data.mysql.redis.enabled", false);
        redisServer = configuration.getString("data.mysql.redis.server", "127.0.0.1");
        redisPort = configuration.getInt("data.mysql.redis.port", 6379);
        redisPassword = configuration.getString("data.mysql.redis.password", "");
        registerEconomy = configuration.getBoolean("data.register-economy", false);
        balanceTopUpdateInterval = configuration.getInt("data.balance-top-update-interval", 5);
    }

    public int getVersion() {
        return this.version;
    }

    public boolean isCheckForUpdates() {
        return this.checkForUpdates;
    }

    public String getOnlineMode() {
        return this.onlineMode;
    }

    public boolean isAltPrevention() {
        return this.altPrevention;
    }

    public int getDefaultBalance() {
        return this.defaultBalance;
    }

    public long getSendMin() {
        return this.sendMin;
    }

    public long getSendMax() {
        return this.sendMax;
    }

    public boolean isOpenSelectedEnabled() {
        return this.openSelectedEnabled;
    }

    public String getOpenSelectedShop() {
        return this.openSelectedShop;
    }

    public String getConfirmPurchaseTitle() {
        return this.confirmPurchaseTitle;
    }

    public String getConfirmPurchaseConfirm() {
        return this.confirmPurchaseConfirm;
    }

    public String getConfirmPurchaseCancel() {
        return this.confirmPurchaseCancel;
    }

    public int getClickDelay() {
        return this.clickDelay;
    }

    public boolean isCheckInventoryFull() {
        return this.checkInventoryFull;
    }

    public boolean isLogPurchases() {
        return this.logPurchases;
    }

    public boolean isMysqlEnabled() {
        return this.mysqlEnabled;
    }

    public String getMysqlUsername() {
        return this.mysqlUsername;
    }

    public String getMysqlPassword() {
        return this.mysqlPassword;
    }

    public String getMysqlHostname() {
        return this.mysqlHostname;
    }

    public String getMysqlPort() {
        return this.mysqlPort;
    }

    public String getMysqlDatabase() {
        return this.mysqlDatabase;
    }

    public String getMysqlTable() {
        return this.mysqlTable;
    }

    public String getMysqlUrl() {
        return this.mysqlUrl;
    }

    public boolean isRedisEnabled() {
        return this.redisEnabled;
    }

    public String getRedisServer() {
        return this.redisServer;
    }

    public int getRedisPort() {
        return this.redisPort;
    }

    public String getRedisPassword() {
        return this.redisPassword;
    }

    public boolean isRegisterEconomy() {
        return this.registerEconomy;
    }

    public int getBalanceTopUpdateInterval() {
        return this.balanceTopUpdateInterval;
    }

    private class Converter2_3 implements Converter {

        Converter2_3() {}

        @Override
        public Map<String, String> renamedKeys() {
            final Map<String, String> keys = new HashMap<>();
            keys.put("use-default.enabled", "shop.open-selected.enabled");
            keys.put("use-default.shop", "shop.open-selected.shop");
            keys.put("mysql.enabled", "data.mysql.enabled");
            keys.put("mysql.hostname", "data.mysql.hostname");
            keys.put("mysql.port", "data.mysql.port");
            keys.put("mysql.username", "data.mysql.username");
            keys.put("mysql.password", "data.mysql.password");
            keys.put("mysql.database", "data.mysql.database");
            keys.put("click-delay", "shop.click-delay");
            keys.put("update-balance-top", "data.balance-top-update-interval");
            keys.put("vault-hooks", "data.register-economy");
            return keys;
        }
    }
}
