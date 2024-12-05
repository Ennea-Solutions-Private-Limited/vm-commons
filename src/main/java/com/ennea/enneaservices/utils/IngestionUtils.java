package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.constants.Constants;
import com.ennea.enneaservices.model.Address;
import com.ennea.enneaservices.model.District;
import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.model.UserSequence;
import com.ennea.enneaservices.repository.DistrictRepository;
import com.ennea.enneaservices.repository.UserSequenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class IngestionUtils {

    private final Logger logger = LoggerFactory.getLogger(IngestionUtils.class);
    final Map<String, District> found_district_cache = new HashMap<>();
    final Map<String, District> missing_district_cache = new HashMap<>();

    private final DistrictRepository districtRepository;

    private final UserSequenceRepository userSequenceRepository;

    @Autowired
    public IngestionUtils(DistrictRepository districtRepository, UserSequenceRepository userSequenceRepository) {
        this.districtRepository = districtRepository;
        this.userSequenceRepository = userSequenceRepository;
    }

    private void warn(String fmt, Object... data) {
        logger.warn(String.format(fmt, data));
    }

    public Address create_address(String which, Map<String, String> record) {
        String district = record.getOrDefault("district", null);
        String state = record.getOrDefault("state", null);
        int pincode = getint(record, "pincode");
        District dist = search_district(state, district, which);
        final Address address = new Address();
        address.setAddressLine1(record.getOrDefault("address_line1", ""));
        address.setAddressLine2(record.getOrDefault("address_line2", ""));
        address.setCity(record.get("city"));
        address.setDistrict(dist);
        address.setPincode(pincode);
        return address;
    }

    public void set_address(User user, Address address) {
        if(!address.compare(user.getAddress())){
            user.setAddress(address);
        }
    }

    public int getint(Map<String, String> record, String name) {
        try {
            return Integer.parseInt(record.getOrDefault(name, "-1"));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private District search_district(String state, String district, String when) {
        District dist;
        if(found_district_cache.containsKey(district)){
            dist = found_district_cache.get(district);
        } else if(missing_district_cache.containsKey(district)){
            dist = missing_district_cache.get(district);
            if(dist == null){
                warn(when + ": Unknown district (cached) " + district);
            }
        } else{
            Optional<District> tmp = districtRepository.findByName(district, state);
            if(tmp.isEmpty()){
                warn(when + ": Unknown district " + district);
                dist = districtRepository.findByName(Constants.UNKNOWN_DISTRICT, Constants.UNKNOWN_STATE).get();
                missing_district_cache.put(district, dist);
            } else{
                dist = tmp.get();
                found_district_cache.put(district, dist);
            }
        }
        return dist;
    }


    public void set_uuid(User user) {
        UserSequence userSequence = userSequenceRepository.save(new UserSequence());
        user.setUuid(userSequence.getSequenceId());
    }

    @SuppressWarnings("unused")
    public Long getLong(Map<String, String> record, String name) {
        try {
            return Long.parseLong(record.getOrDefault(name, "-1"));
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

}
